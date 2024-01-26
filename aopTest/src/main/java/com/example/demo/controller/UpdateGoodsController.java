package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.GoodsDAO;
import com.example.demo.vo.GoodsVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
@RequestMapping("/updateGoods")
@Setter
public class UpdateGoodsController {
	@Autowired
	GoodsDAO dao;
	
	@RequestMapping(method = RequestMethod.GET)
	public void form(HttpServletRequest req, int no, Model model) {
		model.addAttribute("g", dao.detail(no));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submit(HttpServletRequest req, GoodsVO g) {
		ModelAndView mav = new ModelAndView("redirect:/listGoods");
		String path = req.getServletContext().getRealPath("images");
		String oldFname = req.getParameter("fname");
		MultipartFile uploadFile = g.getUploadFile();
		String fname = uploadFile.getOriginalFilename();
		System.out.println(fname);
		
		if (fname != null && !fname.equals("")) {
			try {
				byte[] data = uploadFile.getBytes();
				FileOutputStream fos = new FileOutputStream(path+"/"+fname);
				fos.write(data);
				fos.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			
			g.setFname(fname);
		}
		
		int re = dao.update(g);
		if (re == 1 && fname != null && !fname.equals("")) {
			File file = new File(path+"/"+oldFname);
			file.delete();
		}
		
		return mav;
	}
}
