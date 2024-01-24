package com.example.demo.contorller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;

@Controller
@Setter
public class MailController {
	@Autowired
	private MailSender mailSender;
	
	@GetMapping("/sendMail")
	@ResponseBody
	public String send() {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(""); // 보내는 이
		mailMessage.setTo(""); // 받는 이
		mailMessage.setSubject("메일 보내기 연습"); // 제목
//		mailMessage.setText("메일 연습"); // 메일 본문
		
		Random r = new Random();
		int a = r.nextInt(10);
		int b = r.nextInt(10);
		int c = r.nextInt(10);
		int d = r.nextInt(10);
		
		String data = a+""+b+""+c+""+d;
		mailMessage.setText(data);
		
		try {
			// 메일 보내기
			mailSender.send(mailMessage);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "OK";
	}
}
