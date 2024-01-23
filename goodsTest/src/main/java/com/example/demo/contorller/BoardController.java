package com.example.demo.contorller;

import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.BoardDAO;
import com.example.demo.vo.BoardVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
@Setter
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping("/insertBoard")
	public void form(Model model, String no) {
		int bno = 0;
		if (no != null && !no.equals("")) {
			bno = Integer.parseInt(no);
		}
		model.addAttribute("no", bno);
		
	}
	
	@PostMapping("/insertBoard")
	public String submit(BoardVO b, HttpServletRequest req) {
		String view = "redirect:/listBoard";
		String path = req.getServletContext().getRealPath("upload");
		String fname = null;
		MultipartFile uploadFile = b.getUploadFile();
		fname = uploadFile.getOriginalFilename();
		b.setFname(fname);
		
		// 새글이라 치고 처리
		int no = dao.boardNextNo();
		int b_ref = no;
		int b_level = 0;
		int b_step = 0;		
		
		// 0일경우 새글 아닐경우 답글
		int pno = b.getNo();
		if (pno != 0) {
			BoardVO p = dao.detailBoard(pno);
			b_ref = p.getB_ref();
			b_level = p.getB_level();
			b_step = p.getB_step();
			
			dao.updateStep(b_ref, b_step);
			
			b_level++;
			b_step++;
		}

		b.setNo(no);
		b.setB_ref(b_ref);
		b.setB_level(b_level);
		b.setB_step(b_step);
		b.setIp(req.getLocalAddr());
		
		int re = dao.insertBoard(b);
		if (re == 1 && fname != null && !fname.equals("")) {
			try {
				byte[] data = uploadFile.getBytes();
				FileOutputStream fos = new FileOutputStream(path+"/"+fname);
				fos.write(data);
				fos.close();
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		return view;
	}
	
	@GetMapping("/detailBoard")
	public void detailBoard(Model model, int no) {
		model.addAttribute("b", dao.detailBoard(no));
	}
	
	@GetMapping("/listBoard")
	public void listBoard(Model model,
			@RequestParam(value="pageNum", defaultValue="1") String pageNum) {
		int maxRecord = dao.maxRecord();
		int page = 10;
		int totalPage = maxRecord / page + 1;
		
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("list", dao.listBoard(pageNum));
	}
}
