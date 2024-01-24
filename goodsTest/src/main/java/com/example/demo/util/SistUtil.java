package com.example.demo.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.dao.EmpDAO;
import com.example.demo.vo.EmpVO;

import kr.co.youiwe.webservice.BitSms;
import lombok.Setter;

@Component // 객체를 생성해, 자동으로 스캔
@EnableScheduling // 스케줄링 설정
@Setter
public class SistUtil {
	
	@Autowired
	private EmpDAO dao;
	@Autowired
	private BitSms sms;
	@Autowired
	private MailSender mailSender;
	
	// 10초마다 동작하는 메소드
//	@Scheduled(fixedRate = 10000)
	// 지정한 시간에 동작
	@Scheduled(cron="30 35 16 * * ?")
	public void pro() {
		EmpDAO dao = new EmpDAO();
		List<EmpVO> list = dao.listEmp();
		
		for (EmpVO e: list) {
			if (e.getEmail() != null && !e.getEmail().equals("")) {
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setFrom("somsom150615@gmail.com"); // 보내는 이
				mailMessage.setTo(e.getEmail()); // 받는 이
				mailMessage.setSubject("급여명세서 [담당자:김웅평]"); // 제목
				mailMessage.setText(e.getEname()+"님, 2024년 1월 급여는 " + e.getSalary()+"원입니다");
				
				mailSender.send(mailMessage);
			}
		}
	}
}
