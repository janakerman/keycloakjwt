package com.janakerman.keycloakjwt.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    @Value("${keycloakjwt.realm}")
    String realm;

    @Value("${keycloakjwt.keycloakUrl}")
    String keycloakUrl;

    @Value("${keycloakjwt.clientSecret}")
    String clientSecret;

    @Value("${keycloakjwt.clientId}")
    String clientId;

    @Value("${keycloakjwt.service-account.username}")
    String username;

    @Value("${keycloakjwt.service-account.username}")
    String password;

}
