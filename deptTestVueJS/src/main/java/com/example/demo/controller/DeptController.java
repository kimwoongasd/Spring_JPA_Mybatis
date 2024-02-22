package com.example.demo.controller;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Dept;
import com.example.demo.service.DeptService;

import jakarta.persistence.PostRemove;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@RestController
@Setter
public class DeptController {
	
	@Autowired
	private DeptService ds;
	
	@GetMapping("/listDept")
	public List<Dept> listDept(){
		return ds.listDept();
	}
	
	
	@PostMapping("/insertDept")
	public String inserDept(Dept d, HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("images");
		System.out.println("path:"+path);
		MultipartFile uploadFile = d.getUploadFile();
		String fname = uploadFile.getOriginalFilename();
		System.out.println(fname);
		System.out.println(d);
		
		d.setFname(fname);
		ds.insertDept(d);
		
		if (!fname.equals("") && fname != null) {
			try {
				FileOutputStream fos = new FileOutputStream(path+"/"+fname);
				FileCopyUtils.copy(uploadFile.getBytes(), fos);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return "OK";
	}
	
//	@PostMapping("/updateDept")
//	public String updateDept(Dept d, HttpServletRequest req) {
//		String path = req.getServletContext().getRealPath("images");
//		MultipartFile uploadFile = d.getUploadFile();
//		String fname = uploadFile.getOriginalFilename();
//		
//		return "OK";
//	}
//	
//	@PostMapping("/deleteDept")
//	public String deleteDept(int dno) {
//		ds.deleteDept(dno);
//		
//		return "OK";
//	}
}
