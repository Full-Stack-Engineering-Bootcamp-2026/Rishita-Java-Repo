package com.cdac.servicelayer.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.cdac.servicelayer.repository.MessageRepository;

@Service
public class GreetingService {

    private final MessageRepository messageRepository;

    
    public GreetingService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public String greet(String name) {
        return "Hello " + name + "! Welcome to My Spring Boot";
    }

    public List<String> getMessages() {
        return messageRepository.getMessages();
    }
}
