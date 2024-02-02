package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {
	
	@GetMapping("/test2/{name}/{age}")
	public String test2(@PathVariable String name,@PathVariable int age) {
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
		return "test2";
	}

	@GetMapping("/test1")
	public void test1(String name, int age) {
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
	}
}
