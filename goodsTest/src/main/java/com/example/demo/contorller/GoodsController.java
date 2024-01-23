package com.example.demo.contorller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.BoardDAO;
import com.example.demo.dao.GoodsDAO;
import com.example.demo.vo.GoodsVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
@Setter
public class GoodsController {
	
	@Autowired
	private GoodsDAO dao;	
	

	@GetMapping("/listGoods")
	public void test(Model model) {
		model.addAttribute("list", dao.findAll());
	}
	
	@GetMapping("/detailGoods")
	public void detail(Model model, int no) {
		model.addAttribute("g", dao.detail(no));
	}
	
	@GetMapping("/deleteGoods")
	public String delete(int no, HttpServletRequest req) {
		String view = "redirect:/listGoods";
		String path = req.getServletContext().getRealPath("images");
		String fname = dao.detail(no).getFname();
		int re = dao.delete(no);
		if (re == 1 && fname != null) {
			File file = new File(path+"/"+fname);
			System.out.println(file);
			file.delete();
		}
		
		return view;
	}
}
