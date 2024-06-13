package com.alibou.ecommerce.payment;

import com.alibou.ecommerce.notification.NotificationProducer;
import com.alibou.ecommerce.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest paymentRequest) {
        Payment payment = paymentRepository.save(paymentMapper.toPayment(paymentRequest));
        PaymentNotificationRequest paymentNotificationRequest = new PaymentNotificationRequest(
                paymentRequest.getOrderReference(),
                paymentRequest.getAmount(),
                paymentRequest.getPaymentMethod(),
                paymentRequest.getCustomer().getFirstname(),
                paymentRequest.getCustomer().getLastname(),
                paymentRequest.getCustomer().getEmail()
        );
        notificationProducer.sendNotification(paymentNotificationRequest);
        return payment.getId();
    }
}
