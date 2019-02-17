package com.janakerman.keycloakjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConditionalOnProperty(name = "keycloakjwt.mode", havingValue = "client")
public class RequestService {

    private final TokenService tokenService;

    public RequestService(@Autowired TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Scheduled(fixedDelay = 1000)
    public void makeRequest() {
        System.out.println("requesting");

        tokenService.getAccessToken().getToken();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://server:8080/greeting", String.class);

        System.out.println(response);
    }

}
