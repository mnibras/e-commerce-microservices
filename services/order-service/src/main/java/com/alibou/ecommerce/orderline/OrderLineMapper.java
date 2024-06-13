package com.alibou.ecommerce.orderline;

import com.alibou.ecommerce.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.getId())
                .productId(orderLineRequest.getProductId())
                .quantity(orderLineRequest.getQuantity())
                .order(Order.builder()
                        .id(orderLineRequest.getOrderId())
                        .build())
                .build();
    }
}
