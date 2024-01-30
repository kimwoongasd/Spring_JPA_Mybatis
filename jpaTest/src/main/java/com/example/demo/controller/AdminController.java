package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Member;

import lombok.Setter;

@Controller
@Setter
public class AdminController {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/admin/index")
	public void index() {		
	}
	
	
	@GetMapping("/admin/member/list")
	public void memberList(Model model) {
		model.addAttribute("list", memberDAO.findAll());
	}
	
	
	@GetMapping("/admin/member/delete/{id}")
	public ModelAndView deleteMember(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/member/list");
		memberDAO.deleteById(id);
		return mav;
	}
	
	@GetMapping("/admin/member/update/{id}")
	public ModelAndView updateMemberForm(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("/admin/member/update");
		mav.addObject("m", memberDAO.findById(id).get()) ;
		return mav;
	}
	
	@PostMapping("/admin/member/update")
	public ModelAndView updateMemberSubmit(Member m, String update_pwd) {
		ModelAndView mav = new ModelAndView("redirect:/admin/member/list");
		if(update_pwd != null && !update_pwd.equals("")) {
			m.setPwd(passwordEncoder.encode(update_pwd));
		}
		memberDAO.save(m);
		return mav;
	}
	
}















