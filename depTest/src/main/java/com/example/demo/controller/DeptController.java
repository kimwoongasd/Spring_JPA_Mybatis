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
	
	@GetMapping("/insertDept")
	public void insertForm() {
		System.out.println("insertDept get방식 동작함");
	}
	
	@PostMapping("/insertDept")
	public String insertSubmit(DeptVO d, Model model) {
		// listDept로 보내도 값이 없기때문에 redirect:/listDept로 
		// listDept를 다시 실행시킨다
		String view="redirect:/listDept";
		int re = dao.insertDept(d);
		if (re != 1) {
			model.addAttribute("msg", "부서등록에 실패하였습니다");
			view = "error";
		}
		
		return view;
	}
	
	@GetMapping("listDept")
	public void list(Model model) {
		model.addAttribute("list", dao.findAll());
	}
	
	@GetMapping("/updateDept")
	public void updateForm(Model model, int dno) {
		model.addAttribute("d", dao.findDept(dno));
	}
	
	@PostMapping("/updateDept")
	public String updateSubmit(DeptVO d, Model model) {
		String view = "redirect:/listDept";
		int re = dao.updateDept(d);
		if (re != 1) {
			model.addAttribute("msg", "부서수정에 실패하였습니다");
			view = "error";
		}
		
		return view;
	}
	
	@GetMapping("/deleteDept")
	public String delete(int dno, Model model) {
		String view = "redirect:/listDept";
		int re = dao.deleteDept(dno);
		if (re != 1) {
			view = "error";
			model.addAttribute("msg", "삭제에 실패하였습니다");
		}
		
		return view;
	}
}
