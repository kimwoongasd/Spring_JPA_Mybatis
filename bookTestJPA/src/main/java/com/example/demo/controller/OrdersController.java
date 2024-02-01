package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.OrdersVO;
import com.example.demo.service.BookService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrdersService;

import lombok.Setter;

@Controller
@Setter
public class OrdersController {
	
	@Autowired
	private BookService bs;
	@Autowired
	private CustomerService cs;
	@Autowired
	private OrdersService os;
	
	
	@GetMapping("/orders/list")
	public void findAll(Model model, String keyword){
		model.addAttribute("list", os.findAll(keyword));
	}
	
	@GetMapping("/orders/insert")
	public void insert(Model model, HashMap<String, String> map) {
		model.addAttribute("bList", bs.findAll(null));
		model.addAttribute("cList", cs.findAll());
		model.addAttribute("orderid", os.getNextNo());
	}
	
	@PostMapping("/orders/save")
	public String save(OrdersVO o) {
		String view = "redirect:/orders/list";
		os.insert(o);
		
		return view;
	}
}
