package com.socialnetwork.user_service.config;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakClientConfig {

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:9090")
                .realm("social-network")
                .clientId("sn")
                .clientSecret("CKscMhlRFP7AFo58kNSlVcIiimbHIPF1")
                .username("tuandat8103@gmail.com")
                .password("admin")
                .grantType("client_credentials")
                .build();
    }
}