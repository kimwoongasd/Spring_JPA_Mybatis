package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dao.DeptDAO;
import com.example.demo.vo.DeptVO;

import lombok.Setter;

@Controller
@Setter
public class DeptController {
	
	@Autowired
	private DeptDAO dao;
	
	@GetMapping("/listDept")
	public List<DeptVO> listDept(Model model) {
		model.addAttribute("list", dao.findAll());
		return dao.findAll();
	}
	
//	@GetMapping("/insert")
//	@ResponseBody
//	public String Insert(DeptVO d) {
//		return dao.insert(d)+"";
//	}
//	
//	@GetMapping("/update")
//	@ResponseBody
//	public String updateDept(DeptVO d) {
//		return dao.update(d)+"";
//	}
//	
//	@GetMapping("/delete")
//	@ResponseBody
//	public String deleteDept(int dno) {
//		return dao.delete(dno)+"";
//	}
}
