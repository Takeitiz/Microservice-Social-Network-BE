package com.socialnetwork.user_service.kafka;

public record RequestNotification(
        String userName,
        String friendName,
        String friendEmail
) {
}
