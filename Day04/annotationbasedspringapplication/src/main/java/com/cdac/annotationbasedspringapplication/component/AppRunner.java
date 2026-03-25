package com.cdac.annotationbasedspringapplication.component;

import org.springframework.stereotype.Component;

import com.cdac.annotationbasedspringapplication.service.MessageService;

import jakarta.annotation.PostConstruct;

@Component
public class AppRunner {
	private final MessageService messageService;
	private final String newBean;
	
	public AppRunner(MessageService messageService, String newBean) {
		this.messageService=messageService;
		this.newBean = newBean;
	}
	
	@PostConstruct
	public void run() {
		System.out.println(messageService.getMessage());
		System.out.println(newBean);

	}
}
