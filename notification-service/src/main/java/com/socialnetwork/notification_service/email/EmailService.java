package com.socialnetwork.notification_service.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

import static com.socialnetwork.notification_service.email.EmailTemplates.REQUEST_NOTIFICATION;
import static java.nio.charset.StandardCharsets.UTF_8;
import org.thymeleaf.context.Context;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendRequestNotification(String destinationEmail,
                                        String userName,
                                        String friendName
                                        ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, UTF_8.name());

        messageHelper.setFrom("tuandat8103@gmail.com");
        final String templateName = REQUEST_NOTIFICATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("userName", userName);
        variables.put("friendName", friendName);

        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(REQUEST_NOTIFICATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);

        } catch (MessagingException e) {

        }
    }


}
