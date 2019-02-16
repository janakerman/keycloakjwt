package com.janakerman.keycloakjwt.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RequestService {

    @Scheduled(fixedDelay = 1000)
    public void makeRequest() {
        System.out.println("asdf");
    }

}
