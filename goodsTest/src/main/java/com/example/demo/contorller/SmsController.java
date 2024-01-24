package com.example.demo.contorller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.youiwe.webservice.BitSms;
import lombok.Setter;

@Controller
@Setter
public class SmsController {
	@Autowired
	private BitSms sms;
	
	@GetMapping("/sendSms")
	@ResponseBody
	public String sendSms() {
		String from = ""; // 등록된 전화번호
		String to = ""; // 받는사람 전화번호
		String msg = "";
		Random r = new Random();
		
		int a = r.nextInt(10);
		int b = r.nextInt(10);
		int c = r.nextInt(10);
		int d = r.nextInt(10);
		
		msg = a+""+b+""+c+""+d;
		
		sms.sendMsg(from, to, msg);
		return "ok";
	}
}
