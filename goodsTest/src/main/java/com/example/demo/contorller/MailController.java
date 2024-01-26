package com.example.demo.contorller;

import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;

@Controller
@Setter
public class MailController {
//	@Autowired
//	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("/sendHTML")
	@ResponseBody
	public String sendHtml() {
		mailSender.send(new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// TODO Auto-generated method stub
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				
				String text = "<h2>회원가입 성공</h2>";
				text += "<table border='1'>";
				text += "<tr>";
				text += "<td>아이디</td>";
				text += "<td>tiger</td>";
				text += "<td>이름</td>";
				text += "<td>홍길동</td>";
				text += "</tr>";
				text += "</table>";
				text += "<br><br>";
				// cid = addInline(id, 경로)
				// cid에서 설정한 값과 addInline의 id값이 서로 같아야한다
				text += "<img src=''cid:ball'>";
				
				message.setFrom("");
				message.setTo("");
				message.setSubject("회원가입 완료");
				message.setText(text, true);
				
				
				message.addInline("ball", new ClassPathResource("com/example/demo/sist/ball1.jpg"));
				message.addAttachment("result.txt", new ClassPathResource("com/example/demo/sist/result.txt"));
				
			}
		});
		return "OK";
	}
	
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
