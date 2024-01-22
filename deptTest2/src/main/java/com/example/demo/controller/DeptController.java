package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dao.DeptDAO;
import com.example.demo.vo.DeptVO;

@Controller
public class DeptController {
	@Autowired
	private DeptDAO dao;

	public void setDao(DeptDAO dao) {
		this.dao = dao;
	}

	@GetMapping("/listDept")
	public void listDept(Model model) {
		model.addAttribute("list", dao.findAll());
	}
	
	@GetMapping("/insertDept")
	public void insertForm() {
	}
	
	@PostMapping("/insertDept")
	public String insertSubmit(DeptVO d, Model model) {
		String view = "redirect:/listDept";
		int re = dao.insert(d);
		
		if (re != 1) {
			view = "error";
			model.addAttribute("msg", "추가실패");
		}
		
		return view;
	}
	
	@GetMapping("/updateDept")
	public void updateForm(int dno, Model model) {
		DeptVO d = dao.findByDno(dno);
		model.addAttribute("d", d);
	}
	
	@PostMapping("/updateDept")
	public String updateSubmit(DeptVO d, Model model) {
		int re = -1;
		String view = "redirect:/listDept";
		
		re = dao.update(d);
		if (re != 1) {
			view = "error";
			model.addAttribute("msg", "수정실패");
		}
		
		return view;
	}
	
	@GetMapping("/deleteDept")
	public String delete(int dno, Model model) {
		int re = -1;
		String view = "redirect:/listDept";
		re = dao.delete(dno);
		if (re != 1) {
			view = "error";
			model.addAttribute("msg", "삭제실패");
		}
		
		return view;
	}
}
