package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.BookDAO;
import com.example.demo.vo.BookVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller // controller와 ResponseBody를 합친것
@Setter
public class BookController {
	@Autowired
	private BookDAO dao;
	
	@GetMapping("/listBook")
	@ResponseBody
	public List<BookVO> listBook(HttpServletRequest req){
		return dao.findAll();
	}
	
	@GetMapping("/detailBook")
	public void detailBook(HttpServletRequest req, int bookid, Model model) {
		model.addAttribute("b", dao.findByNo(bookid));
	}
}
