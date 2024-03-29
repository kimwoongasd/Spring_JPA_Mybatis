package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.BookDAO;

@Controller
public class BookController {
	
	@Autowired
	BookDAO dao;

	public void setDao(BookDAO dao) {
		this.dao = dao;
	}
	
	@GetMapping("/listBook")
	public void listbook(Model model) {// 상태유지를 위해 model을 받는다
		model.addAttribute("list", dao.findAll());
	}
}
