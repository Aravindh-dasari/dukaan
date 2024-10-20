package com.dukaan.clone.user_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users")
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World, Lets start our Dukaan";
    }
}
