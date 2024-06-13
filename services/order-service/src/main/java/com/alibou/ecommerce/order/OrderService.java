package com.alibou.ecommerce.order;

import com.alibou.ecommerce.customer.CustomerClient;
import com.alibou.ecommerce.customer.CustomerResponse;
import com.alibou.ecommerce.exception.BusinessException;
import com.alibou.ecommerce.kafka.OrderConfirmation;
import com.alibou.ecommerce.kafka.OrderProducer;
import com.alibou.ecommerce.orderline.OrderLineRequest;
import com.alibou.ecommerce.orderline.OrderLineService;
import com.alibou.ecommerce.payment.PaymentClient;
import com.alibou.ecommerce.payment.PaymentRequest;
import com.alibou.ecommerce.product.ProductClient;
import com.alibou.ecommerce.product.PurchaseRequest;
import com.alibou.ecommerce.product.PurchaseResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest orderRequest) {
        // check the customer --> customer-service --> OpenFeign
        CustomerResponse customerResponse = customerClient.findCustomerById(orderRequest.getCustomerId())
                .orElseThrow(() -> new BusinessException(
                        "Cannot create order:: No customer exists with provided Id:" + orderRequest.getCustomerId()));

        // purchase the products --> product-service --> Rest Template
        List<PurchaseResponse> purchasedProducts = productClient.purchaseProducts(orderRequest.getProducts());

        // persist order
        Order order = orderRepository.save(orderMapper.toOrder(orderRequest));

        // persist orderLines
        for (PurchaseRequest purchaseRequest : orderRequest.getProducts()) {
            orderLineService.saveOrderLine(new OrderLineRequest(
                    null,
                    order.getId(),
                    purchaseRequest.getProductId(),
                    purchaseRequest.getQuantity())
            );
        }

        // process payment --> payment-service
        PaymentRequest paymentRequest = new PaymentRequest(
                orderRequest.getAmount(),
                orderRequest.getPaymentMethod(),
                orderRequest.getId(),
                orderRequest.getReference(),
                customerResponse
        );
        paymentClient.createPayment(paymentRequest);

        // send the order confirmation through kafka--> notification-service
        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                orderRequest.getReference(),
                orderRequest.getAmount(),
                orderRequest.getPaymentMethod(),
                customerResponse,
                purchasedProducts
        ));

        return order.getId();
    }

    public List<OrderResponse> findAllOrders() {
        return this.orderRepository.findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .toList();
    }

    public OrderResponse findById(Integer id) {
        return this.orderRepository.findById(id)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }
}
