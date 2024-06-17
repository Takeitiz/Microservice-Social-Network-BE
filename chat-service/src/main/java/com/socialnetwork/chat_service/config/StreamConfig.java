package com.socialnetwork.chat_service.config;

import io.getstream.chat.java.services.framework.Client;
import io.getstream.chat.java.services.framework.DefaultClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class StreamConfig {

    private String apiKey = "42zja5qw98p3";

    private String apiSecret = "kum3udpuztr63zgexhd7muz532bj54cv7q2rp4uyh9k7rph6fv399zgnh4w3yagt";

    @Bean
    public Client streamClient() {
        var properties = new Properties();
        properties.put(DefaultClient.API_KEY_PROP_NAME, apiKey);
        properties.put(DefaultClient.API_SECRET_PROP_NAME, apiSecret);
        var client = new DefaultClient(properties);
        DefaultClient.setInstance(client);
        return client;
    }
}
