package com.rishita.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello, I am Rishita Pathak!";
	}
	
	@GetMapping("/info")
	public String info() {
		return "CDAC Fresher 2026-Rishita Pathak";
	}
}
