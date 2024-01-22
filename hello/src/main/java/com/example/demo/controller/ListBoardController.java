package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListBoardController {
	
	@GetMapping("/listBoard")
	public void list(Model model) {
		model.addAttribute("name", "kim");
		model.addAttribute("age", 20);
	}
	
//	@GetMapping("/listBoard")
//	public String list(Model model) {
//		model.addAttribute("name", "kim");
//		model.addAttribute("age", 20);
//		return "listBoard";
//	}
	
//	public ModelAndView list() {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("name", "홍길동");
//		mav.addObject("age", "20");
//		mav.setViewName("listBoard");
//		return mav;
//	}
}
