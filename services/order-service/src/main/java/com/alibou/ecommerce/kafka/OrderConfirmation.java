package com.alibou.ecommerce.kafka;

import com.alibou.ecommerce.customer.CustomerResponse;
import com.alibou.ecommerce.order.PaymentMethod;
import com.alibou.ecommerce.product.PurchaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmation {

    private String orderReference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private CustomerResponse customer;
    private List<PurchaseResponse> products;

}