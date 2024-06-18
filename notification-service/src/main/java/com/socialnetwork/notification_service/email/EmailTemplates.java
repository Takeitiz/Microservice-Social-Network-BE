package com.socialnetwork.notification_service.email;

import lombok.Getter;

public enum EmailTemplates {

    REQUEST_NOTIFICATION("request-notification.html", "Request sent successfully");

    @Getter
    private final String template;
    @Getter
    private final String subject;
    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }

}
