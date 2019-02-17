package com.janakerman.keycloakjwt.service;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final KeycloakConfig keycloakConfig;

    public TokenService(@Autowired KeycloakConfig config) {
        this.keycloakConfig = config;
    }

    AccessTokenResponse getAccessToken() {
        return keycloakInstance().tokenManager().getAccessToken();
    }

    private Keycloak keycloakInstance() {
        System.out.println(keycloakConfig.keycloakUrl);
        return KeycloakBuilder.builder()
            .realm(keycloakConfig.realm)
            .serverUrl(keycloakConfig.keycloakUrl)
            .clientId(keycloakConfig.clientId)
            .clientSecret(keycloakConfig.clientSecret)
            .username(keycloakConfig.username)
            .password(keycloakConfig.password)
            .build();
    }

}
