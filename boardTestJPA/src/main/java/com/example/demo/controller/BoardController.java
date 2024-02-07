package com.example.demo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;
import com.example.demo.service.BoardService;
import com.example.demo.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class BoardController {
	
	@Autowired
	private BoardService bs;
	@Autowired
	private MemberService ms;
	private int page = 0;
	private int pageSize = 10;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/board/delete/{no}")
	public String deleteForm(Model model, @PathVariable int no) {
		model.addAttribute("b", bs.getById(no));
		return "/board/delete";
	}
	
	@PostMapping("/board/delete")
	public String deleteSubmit(int no, String pwd, HttpServletRequest req) {
		String view = "redirect:/board/list";
		String path = req.getServletContext().getRealPath("images");
		Board b = bs.getById(no);
		int re = bs.deleteBoard(no, pwd);
		if (re == 1 && b.getFname() != null && !b.getFname().equals("")) {
				File file = new File(path+"/"+b.getFname());
				file.delete();
			}
		
		return view;
	}
	
	@GetMapping("/board/update/{no}")
	public String updateForm(Model model, @PathVariable int no) {
		model.addAttribute("b", bs.getById(no));
		return "/board/update";
	}
	
	@PostMapping("/board/update")
	public String updateSubmit(Board b, HttpServletRequest req) {
		String view = "redirect:/board/list";
		String path = req.getServletContext().getRealPath("images");
		String oldFname = req.getParameter("oldFname");
		
		MultipartFile uploadFile = b.getUploadFile();
		String fname = uploadFile.getOriginalFilename();
		b.setFname(oldFname);
		
		if (fname != null && !fname.equals("")) {
			b.setFname(fname);
			try {
			FileOutputStream fos = new FileOutputStream(path+"/"+fname);
			FileCopyUtils.copy(uploadFile.getBytes(), fos);
			if (oldFname != null && !oldFname.equals("")) {
				File file = new File(path+"/"+oldFname);
				file.delete();
			}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}		
		
		bs.updateBoard(b);
		
		return view;
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
		System.out.println(b.getMember().getId());
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
	
	
	
	// 시큐리티 환경설정파일에서 로그인을 성공했을 때 이동할 URL인 이곳에서
	// 로그인한 회원의 정보를 상태 유지
	// URI방식으로 요청할 경우 view를 설정하거나 STring 반환
	@GetMapping(value ={"/board/list/{pageNum}", "/board/list", "/board/list/{writer}/{pageNum}"})
	public String list(HttpSession session, Model model,@PathVariable(required = false) Integer pageNum, @PathVariable(required = false) String writer){
		page = bs.getTotalRecord() / pageSize +1;
		if (pageNum == null) {
			pageNum = 1;
		}
		int start = (pageNum - 1) * pageSize + 1;
		int end = pageNum * pageSize;
		
		//인증된(로그인된) 회원의 정보를 갖고 오기 위하여 
		//먼저 시큐리티의 인증객체가 필요합니다.
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		
		//이 인증객체를 통해서 인증된(로그인한) User객체를 받아 옵니다.
		User user = (User)authentication.getPrincipal();
		//이, 인증된 User를 통해서 로그인한 회원의 아이디를 갖고 옵니다.
		String id = user.getUsername();
		
		Member m = ms.findById(id);
		session.setAttribute("m", m);
		
		List<Board> list = bs.findAll(start, end);
		if (writer != null && !writer.equals("")) {
			page = bs.getTotalMyRecord(writer) /5 +1;
			start = (pageNum - 1) * 5 + 1;
			end = pageNum * 5;
			list = bs.mySelectAll(writer, start, end);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("writer", writer);
		model.addAttribute("page", page);
		model.addAttribute("pageNum", pageNum);
		
		return "/board/list";
	}
}
