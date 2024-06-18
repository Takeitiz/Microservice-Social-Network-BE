package com.socialnetwork.user_service.kafka;

import lombok.RequiredArgsConstructor;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProducer {
    private final KafkaTemplate<String, RequestNotification> kafkaTemplate;
    public void sendUserRequestNotification(RequestNotification requestNotification) {
        Message<RequestNotification> message = MessageBuilder
                .withPayload(requestNotification)
                .setHeader(KafkaHeaders.TOPIC, "user-topic")
                .build();

        kafkaTemplate.send(message);
    }
}
