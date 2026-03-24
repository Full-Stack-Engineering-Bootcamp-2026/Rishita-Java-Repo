package com.cdac.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

   
    @GetMapping("/hello")
    public String hello() {
        return "Hello, I am Rishita Pathak!";
    }

    
//    @GetMapping("/about")
//    public String about() {
//        return "CDAC Fresher 2026 — Rishita Pathak, I've joined as Trainee Software Enginner";
//    }
    @GetMapping("/about")
    public Map<String,String> about(){
    	Map<String,String> aboutme=new HashMap<>();
    	aboutme.put("name", "Rishita Pathak");
    	aboutme.put("batch", "CDAC 2026");
    	aboutme.put("role", "ML Engineer");
        return aboutme;
    }

   
    @GetMapping("/status")
    public String status() {
        return "Spring Boot is running!";
    }

   
    @GetMapping("/time")
    public String time() {
        return "Current time: " + LocalDateTime.now();
    }
}

