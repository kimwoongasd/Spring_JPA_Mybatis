package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Goods;
import com.example.demo.service.GoodsService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@RestController
@Setter
public class GoodsController {

	@Autowired
	private GoodsService gs;

	@GetMapping("/listGoods")
	public List<Goods> listGoods() {
		return gs.listGoods();
	}

	@PostMapping("/inesrtGoods")
	public String insertGoods(Goods g, HttpServletRequest req, MultipartFile uploadFile) {
		String path = req.getServletContext().getRealPath("images");

		String fname = "";
		try {
			if (uploadFile != null) {
				fname = uploadFile.getOriginalFilename();
				FileOutputStream fos = new FileOutputStream(path + "/" + fname);
				FileCopyUtils.copy(uploadFile.getBytes(), fos);
				fos.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		g.setFname(fname);
		gs.insertGoods(g);
		System.out.println("성공");
		return "OK";
	}

	@PostMapping("/updateGoods")
	public String updateGoods(Goods g, HttpServletRequest req, MultipartFile uploadFile) {
		String path = req.getServletContext().getRealPath("images");
		String oldFname = g.getFname();

		String fname = "";
		g.setFname(oldFname);
		try {
			if (uploadFile != null) {
				fname = uploadFile.getOriginalFilename();
				FileOutputStream fos = new FileOutputStream(path + "/" + fname);
				FileCopyUtils.copy(uploadFile.getBytes(), fos);
				fos.close();
				g.setFname(fname);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		gs.updateGoods(g);
		if (fname != null && !fname.equals("") && oldFname != null && !oldFname.equals("")) {
			File file = new File(path + "/" + oldFname);
			file.delete();
		}
		return "OK";
	}

	@PostMapping("/deleteGoods")
	public String deleteGoods(Goods g, HttpServletRequest req) {
		String path = req.getServletContext().getRealPath("images");
		if (g.getFname() != null && !g.getFname().equals("")) {
			File file = new File(path+"/"+g.getFname());
			file.delete();
		}
		
		gs.deleteGoods(g);
		return "OK";
	}
}
