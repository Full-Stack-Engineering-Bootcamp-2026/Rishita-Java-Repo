package com.cdac.annotationbasedspringapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	
	@Bean
	public String newBean() {
		return "Bean created using @Bean annotation";
	}
}