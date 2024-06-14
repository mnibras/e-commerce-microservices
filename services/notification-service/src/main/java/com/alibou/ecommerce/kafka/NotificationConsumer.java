package com.alibou.ecommerce.kafka;

import com.alibou.ecommerce.email.EmailService;
import com.alibou.ecommerce.kafka.order.OrderConfirmation;
import com.alibou.ecommerce.kafka.payment.PaymentConfirmation;
import com.alibou.ecommerce.notification.Notification;
import com.alibou.ecommerce.notification.NotificationRepository;
import com.alibou.ecommerce.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentConfirmationNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming message from payment-topic Topic: {}", paymentConfirmation);
        Notification notification = Notification.builder()
                .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build();
        notificationRepository.save(notification);

        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.getCustomerEmail(),
                String.format("%s %s", paymentConfirmation.getCustomerFirstname(), paymentConfirmation.getCustomerLastname()),
                paymentConfirmation.getAmount(),
                paymentConfirmation.getOrderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming message from order-topic Topic: {}", orderConfirmation);
        Notification notification = Notification.builder()
                .notificationType(NotificationType.ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build();
        notificationRepository.save(notification);

        emailService.sendOrderConfirmationEmail(
                orderConfirmation.getCustomer().getEmail(),
                String.format("%s %s", orderConfirmation.getCustomer().getFirstname(), orderConfirmation.getCustomer().getLastname()),
                orderConfirmation.getTotalAmount(),
                orderConfirmation.getOrderReference(),
                orderConfirmation.getProducts()
        );
    }

}
