package com.alibou.ecommerce.kafka.order;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;

}
