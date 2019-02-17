package com.janakerman.keycloakjwt.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ConditionalOnProperty(name = "keycloakjwt.mode", havingValue = "server")
public class MyController {

    @GetMapping("/greeting")
    @ResponseBody
    public String greeting() {
        System.out.println("received");
        return "hi";
    }

}
