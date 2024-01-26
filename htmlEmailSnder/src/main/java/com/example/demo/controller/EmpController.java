package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.EmpDAO;
import com.example.demo.vo.EmpVO;

import lombok.Setter;

@Controller
@Setter
public class EmpController {
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private EmpDAO dao;
	
	@GetMapping("/empSend")
	@ResponseBody
	public String EmpListSend() {
		javaMailSender.send(new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				List<EmpVO> list = dao.listEmp();
				
				message.setFrom("");
				message.setTo("");
				message.setSubject("급여명세서");
				String msg = "<h2>2024년 1월</h2>";
				msg += "<table border='1'>";
				msg += "<tr>";
				msg += "<td>사원번호</td>";
				msg += "<td>이름</td>";
				msg += "<td>부서번호</td>";
				msg += "<td>급여</td>";
				msg += "<td>수당</td>";
				msg += "<td>실수령액</td>";
				msg += "</tr>";
				for (EmpVO e : list) {
					msg += "<tr>";
					msg += "<td>"+e.getEno()+"</td>";
					msg += "<td>"+e.getEname()+"</td>";
					msg += "<td>"+e.getDno()+"</td>";
					msg += "<td>"+e.getSalary()+"</td>";
					msg += "<td>"+e.getComm()+"</td>";
					msg += "<td>"+(e.getComm()+e.getSalary())+"</td>";
					msg += "</tr>";
				}				
				msg += "</table>";
				
				
				message.setText(msg, true);
			}
		});
		
		return "OK";
	}
}
