package com.alibou.ecommerce.order;

import org.springframework.stereotype.Service;

@Service
public class OrderMapper {


    public Order toOrder(OrderRequest orderRequest) {
        return Order.builder()
                .id(orderRequest.getId())
                .reference(orderRequest.getReference())
                .totalAmount(orderRequest.getAmount())
                .paymentMethod(orderRequest.getPaymentMethod())
                .customerId(orderRequest.getCustomerId())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
