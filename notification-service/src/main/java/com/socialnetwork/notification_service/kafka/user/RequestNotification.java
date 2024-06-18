package com.socialnetwork.notification_service.kafka.user;

public record RequestNotification(
        String userName,
        String friendName,
        String friendEmail
) {
}
