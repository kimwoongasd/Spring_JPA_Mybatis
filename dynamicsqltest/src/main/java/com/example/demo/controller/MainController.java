package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
	
	// jsp로 가는 코드
	@GetMapping("/")
	public String main(HttpServletRequest req) {
		return "index";
	}
}
