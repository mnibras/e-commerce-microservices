package com.alibou.ecommerce.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResponse {

    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;
}
