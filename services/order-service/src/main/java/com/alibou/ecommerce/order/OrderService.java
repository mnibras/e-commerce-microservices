package com.alibou.ecommerce.order;

import com.alibou.ecommerce.customer.CustomerClient;
import com.alibou.ecommerce.customer.CustomerResponse;
import com.alibou.ecommerce.exception.BusinessException;
import com.alibou.ecommerce.orderline.OrderLineRequest;
import com.alibou.ecommerce.orderline.OrderLineService;
import com.alibou.ecommerce.product.ProductClient;
import com.alibou.ecommerce.product.PurchaseRequest;
import com.alibou.ecommerce.product.PurchaseResponse;
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

    public Integer createOrder(OrderRequest orderRequest) {
        // check the customer --> customer-service --> OpenFeign
        CustomerResponse customerResponse = customerClient.findCustomerById(orderRequest.getCustomerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with provided Id:" + orderRequest.getCustomerId()));

        // purchase the products --> product-service --> Rest Template
        List<PurchaseResponse> purchaseResponses = productClient.purchaseProducts(orderRequest.getProducts());

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


        // start payment process --> payment-service

        // send the order confirmation through kafka--> notification-service
        return null;
    }

    public List<OrderResponse> findAllOrders() {
        return null;
    }

    public OrderResponse findById(Integer orderId) {
        return null;
    }
}
