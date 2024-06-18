package com.socialnetwork.notification_service.kafka;

import com.socialnetwork.notification_service.email.EmailService;
import com.socialnetwork.notification_service.kafka.user.RequestNotification;
import com.socialnetwork.notification_service.notification.Notification;
import com.socialnetwork.notification_service.notification.NotificationRepository;
import com.socialnetwork.notification_service.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "user-topic")
    public void consumeRequestNotification(RequestNotification requestNotification) throws MessagingException {
        repository.save(
                Notification.builder()
                        .type(NotificationType.REQUEST_NOTIFICATION)
                        .notificationDate(LocalDateTime.now())
                        .requestNotification(requestNotification)
                        .build()
        );

        emailService.sendRequestNotification(
                requestNotification.friendEmail(),
                requestNotification.userName(),
                requestNotification.friendName()
        );
    }
}
