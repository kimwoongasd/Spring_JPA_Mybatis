package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dao.GoodsDAO;
import com.example.demo.entity.GoodsVO;
import com.example.demo.service.GoodsService;

import lombok.Setter;

@Controller
@Setter
public class GoodsController {
	
	@Autowired
	private GoodsService gs;
	
	@GetMapping("/list")
	public void list(Model model) {
		model.addAttribute("list", gs.findAll());
	}
	
	@PostMapping("/save")
	public String save(GoodsVO g) {
		String view = "redirect:/list";
		gs.save(g);
		return view;
	}
}
