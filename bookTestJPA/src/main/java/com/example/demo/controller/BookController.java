package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.BookVO;
import com.example.demo.service.BookService;

import jakarta.servlet.http.HttpSession;
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
	public void list(Model model, String keyword, String catgory, String order, HttpSession session) {
		HashMap<String, String> map = new HashMap<String, String>();
		String keyword2 = null;
		String catgory2 = null;
		
		if( session.getAttribute("keyword") != null  ) {
			catgory2 = (String)session.getAttribute("catgory");
			keyword2 = (String)session.getAttribute("keyword");
		}		
		if(keyword != null) {
			catgory2 = catgory;
			keyword2 = keyword;
			session.setAttribute("catgory", catgory);
			session.setAttribute("keyword", keyword);
		}	
		
		map.put("keyword", keyword2);
		map.put("catgory", catgory2);
		map.put("order", order);
		model.addAttribute("list", bs.findAll(map));
	}
	
	@PostMapping("/book/save")
	public String save(BookVO b) {
		String view = "redirect:/book/list";
		bs.save(b);
		return view;
	}
}
