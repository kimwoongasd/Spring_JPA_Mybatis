package com.example.demo.util;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.dao.EmpDAO;
import com.example.demo.vo.EmpVO;

import lombok.Setter;

@Component
@EnableScheduling
@Setter
public class EmpSchedule {
	
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private EmpDAO dao;
	@Autowired
	private EmpUtil empUtil;
	
	@Scheduled(cron="20 11 14 25 * ?")
	public void sendSalaryStatement() {
		List<EmpVO> list = dao.listEmp();
		for(EmpVO e:list) {
			if (e.getEmail() != null && !e.getEmail().equals("")) {
				String text = empUtil.getHTML(e);
				javaMailSender.send(new MimeMessagePreparator() {
					
					@Override
					public void prepare(MimeMessage mimeMessage) throws Exception {
						MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
						message.setFrom("");
						message.setTo(e.getEmail());
						message.setSubject("급여명세서[담당자 : 김웅평]");
						message.setText(text, true);
					}
				});
			} else {
				System.out.println("이메일 오류");
			}
		}
	}
}
