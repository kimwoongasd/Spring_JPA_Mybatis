package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;

import lombok.Setter;

@Controller
@Setter
public class MemberController {

	@Autowired
	private MemberService ms;
	
	@GetMapping("/member/login")
	public void login() {}
	
	
	@GetMapping("/admin/index")
	public void findAll(Model model){
		model.addAttribute("list", ms.findAll());
	}
	
	@GetMapping("/admin/delete/{id}")
	public String delete(@PathVariable String id) {
		String view = "redirect:/admin/index";
		ms.delete(id);
		
		return view;
	}
}
