package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	
	@GetMapping("/listMember")
	public void listMember(Model model) {
		model.addAttribute("title", "쌍용강북교육센터");
		model.addAttribute("year", 2024);
		
		ArrayList<String> list = new ArrayList<>();
		list.add("홍길동");
		list.add("이순신");
		list.add("김유신");
		model.addAttribute("list", list);
	}
}
