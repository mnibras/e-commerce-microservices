package com.alibou.ecommerce.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest paymentRequest) {
        if (paymentRequest == null) {
            return null;
        }
        return Payment.builder()
                .id(paymentRequest.getId())
                .paymentMethod(paymentRequest.getPaymentMethod())
                .amount(paymentRequest.getAmount())
                .orderId(paymentRequest.getOrderId())
                .build();
    }
}
