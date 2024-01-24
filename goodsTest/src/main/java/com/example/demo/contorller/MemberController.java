package com.example.demo.contorller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.MemberVO;

import kr.co.youiwe.webservice.BitSms;
import lombok.Setter;

@Controller
@Setter
public class MemberController {

	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private BitSms sms;
	
	@GetMapping("/sendCodeSms")
	@ResponseBody
	public String sendCodeSms(String phone) {
		String code = "";
		Random r = new Random();
		int a = r.nextInt(10);
		int b = r.nextInt(10);
		int c = r.nextInt(10);
		int d = r.nextInt(10);
		code = a+""+b+""+c+""+d;
		
		String from = ""; // 등록된 전화번호
		String to = phone; // 받는사람 전화번호
		String msg = code;
		sms.sendMsg(from, to, msg);
		return code;
	}
	
	@GetMapping("/sendCodeEmail")
	@ResponseBody
	public String sendCodeEmail(String email) {
		String code= "";
		System.out.println(email);
		
		Random r = new Random();
		int a = r.nextInt(10);
		int b = r.nextInt(10);
		int c = r.nextInt(10);
		int d = r.nextInt(10);
		code = a+""+b+""+c+""+d;
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(""); // 보내는 이
		mailMessage.setTo(email); // 받는 이
		mailMessage.setSubject("인증코드 발송"); // 제목
		mailMessage.setText(code);
		
		try {
			// 메일 보내기
			mailSender.send(mailMessage);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return code;
	}
	
	@GetMapping("/join")
	public void form() {
		
	}
	
	@PostMapping("/join")
	@ResponseBody
	public String submit(MemberVO m) {
		int re = dao.join(m);
		
		return "ok";
	}
}
