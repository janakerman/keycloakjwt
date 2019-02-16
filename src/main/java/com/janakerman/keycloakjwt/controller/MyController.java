package com.janakerman.keycloakjwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/greeting")
    public String greeting() {
        return "greeting";
    }

}
