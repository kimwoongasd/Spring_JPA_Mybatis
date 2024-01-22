package com.example.demo.contorller;

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
// /insertGoods 주소일 때만 컨트롤러 사용
@RequestMapping("/insertGoods")
@Setter
public class InsertGoodsController {
	
	@Autowired
	private GoodsDAO dao;
	
	// get방식일 경우
	@RequestMapping(method = RequestMethod.GET)
	public void form(Model model) {
		model.addAttribute("no", dao.nextNo());
	}
	
	// post 방식일 경우
	@RequestMapping(method = RequestMethod.POST)
	// HttpServletRequest = jakarta
	public ModelAndView submit(GoodsVO g, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView("redirect:/listGoods");
		// 파일 업로드를 위한 경로
		String path = req.getServletContext().getRealPath("images");
		String fname = null;
		System.out.println(path);
		MultipartFile uploadFile = g.getUploadFile();
		// 업로드한 파일이름 가져오기
		fname = uploadFile.getOriginalFilename();
		if (fname != null && !fname.equals("")) {
			try {
				// 업로드한 파일 내용을 가져온다
				byte[] data = uploadFile.getBytes();
				// 파일을 출력하기 위한 설정
				FileOutputStream fos = new FileOutputStream(path + "/" + fname);
				fos.write(data);
				fos.close();
				g.setFname(fname);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		int re = dao.insert(g);
		
		return mav;
	}
}
