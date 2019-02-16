package com.janakerman.keycloakjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KeycloakjwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeycloakjwtApplication.class, args);
	}

}

