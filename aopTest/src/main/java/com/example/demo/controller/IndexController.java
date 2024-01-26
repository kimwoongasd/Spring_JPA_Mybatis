package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
	
	@GetMapping("/")
	public String index(HttpServletRequest req) {
		return "index";
	}
}
