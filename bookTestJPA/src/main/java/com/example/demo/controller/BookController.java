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
	
	
	@GetMapping("/book/delete")
	public String delete(int bookid) {
		String view = "redirect:/list";
		bs.delete(bookid);
		
		return view;
	}
	
	@GetMapping("/book/update")
	public void update(Model model, int bookid) {
		model.addAttribute("b", bs.findById(bookid));
	}
	
	@GetMapping("/book/detail")
	public void detail(int bookid, Model model) {
		model.addAttribute("b", bs.findById(bookid));
	}
	
	@GetMapping("/book/list")
	public void list(Model model) {
		model.addAttribute("list", bs.findAll());
	}
	
	@PostMapping("/book/save")
	public String save(BookVO b) {
		String view = "redirect:/book/list";
		bs.save(b);
		return view;
	}
}
