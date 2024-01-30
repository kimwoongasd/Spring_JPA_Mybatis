package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.BookVO;
import com.example.demo.service.BookService;

import lombok.Setter;

@Controller
@Setter
public class BookController {
	
	@Autowired
	private BookService bs;
	
	@GetMapping("/list")
	public void list(Model model) {
		model.addAttribute("list", bs.findAll());
	}
	
	@PostMapping("/save")
	public String save(BookVO b) {
		String view = "redirect:/list";
		bs.save(b);
		return view;
	}
}
