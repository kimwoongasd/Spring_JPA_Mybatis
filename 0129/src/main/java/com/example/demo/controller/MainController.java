package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	// jsp로 가는 코드
	@GetMapping("/")
	public String main() {
		return "index";
	}
}
