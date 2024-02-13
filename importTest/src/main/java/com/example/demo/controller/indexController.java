package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class indexController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}

}
