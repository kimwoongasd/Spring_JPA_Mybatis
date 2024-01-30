package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.BookDAO;

import lombok.Setter;

@Controller
@Setter
public class BookController {
	
	@Autowired
	private BookDAO dao;
	
	@GetMapping("/listBook")
	public void listBook(Model model) {
		
		model.addAttribute("list", dao.findAll());
	}
	
}
