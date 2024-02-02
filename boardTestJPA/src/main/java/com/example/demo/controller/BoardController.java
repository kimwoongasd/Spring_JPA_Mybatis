package com.example.demo.controller;

import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
@Setter
public class BoardController {
	
	@Autowired
	private BoardService bs;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/board/detail/{no}")
	public String detail(Model model,@PathVariable() int no) {
		model.addAttribute("b", bs.getById(no));
		
		return "/board/detail";
	}
	
	@GetMapping("/board/insert")
	public void insertForm(@RequestParam(value = "no", defaultValue = "0") int no, Model model) {		
		model.addAttribute("r_no", no);
	}
	
	@PostMapping("/board/insert")
	public String insertSubmit(Board b, HttpServletRequest req) {
		String view = "redirect:/board/list";
		String path = req.getServletContext().getRealPath("images");
		int no = bs.getNextNo();
		int b_ref = no;
		int b_level = 0;
		int b_step = 0;
		
		int r_no = b.getNo();
		System.out.println(r_no);
		if (r_no != 0) {
			Board b2 = bs.getById(r_no);
			b_ref = b2.getB_ref();
			b_level = b2.getB_level();
			b_step = b2.getB_step();
			bs.updateStep(b_ref, b_step);
			
			b_level++;
			b_step++;
		}
		
		b.setNo(no);
		b.setB_ref(b_ref);
		b.setB_level(b_level);
		b.setB_step(b_step);
		
		
		MultipartFile uploadFile = b.getUploadFile();
		String fname = null;
		fname = uploadFile.getOriginalFilename();
		b.setFname(fname);
		
		if (!fname.equals("") && fname != null) {
			try {
				byte[] data = uploadFile.getBytes();
				FileOutputStream fos = new FileOutputStream(path+"/"+fname);
				fos.write(data);
				fos.close();
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		
		
		bs.insert(b);
		return view;
	}
	
	@GetMapping("/board/list")
	public List<Board> list(Model model){
		List<Board> list = bs.findAll();
		model.addAttribute("list", list);
		return list;
	}
}
