package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Goods;
import com.example.demo.entity.Pay;
import com.example.demo.service.GoodsService;
import com.example.demo.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class GoodsController {
	
	@Autowired
	private GoodsService gs;
	
	@Autowired
	private MemberService ms;
	
	@GetMapping("/goods/order/{no}")
	public String order(@PathVariable("no") int no, Model model) {
		model.addAttribute("g", gs.getGoods(no));
		return "goods/order";
	}
	
	@PostMapping("/goods/order")
	@ResponseBody
	public String orderinsert() {
		
		return "ok";
	}

	
	@GetMapping("/goods/detail/{no}")
	public String detail(@PathVariable("no") int no, Model model) {
		model.addAttribute("g", gs.getGoods(no));
		return "goods/detail";
	} 
	
	@GetMapping("/goods/list")
	public void list(HttpSession session, Model model) {
		Authentication authentication = 
				SecurityContextHolder
				.getContext()
				.getAuthentication();
		
		User user = (User)authentication.getPrincipal();
		String id = user.getUsername();
		System.out.println(id);
		session.setAttribute("m", ms.findById(id));
		model.addAttribute("list", gs.list());
	}
	
	@GetMapping("/goods/insert")
	public void insertForm() {}
	
	@PostMapping("/goods/insert")
	public String insertSubmit(HttpServletRequest req, Goods g) {
		String view = "redirect:/goods/list";
		String path = req.getServletContext().getRealPath("images");
		
		MultipartFile uploadFile = g.getUploadFile();
		String fname = null; 
		fname = uploadFile.getOriginalFilename();
		
		if (fname != null && !fname.equals("")) {
			g.setFname(fname);
			try {
				FileOutputStream fos = new FileOutputStream(path+"/"+fname);
				FileCopyUtils.copy(uploadFile.getBytes(), fos);
				fos.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		g.setNo(gs.nextNo());
		gs.insert(g);
		return view;
	}
}
