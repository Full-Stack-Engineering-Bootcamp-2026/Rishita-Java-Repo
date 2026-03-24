package com.cdac.servicelayer.controller;


import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cdac.servicelayer.service.GreetingService;

@RestController
public class GreetingController {

    private final GreetingService greetingService;

    
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        return greetingService.greet(name);
    }

    @GetMapping("/messages")
    public List<String> getMessages() {
        return greetingService.getMessages();
    }
}
