package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.db.DBManager;
import com.example.demo.vo.MemberVO;

@SpringBootApplication
public class ScurityBookApplication {
	
	
	// 암호화 인코딩을 위한 객체 생성
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	public static void main(String[] args) {
//		MemberVO m = new MemberVO();
//		m.setId("tiger");
//		// 비밀번호를 인코딩하여 저장
//		m.setPwd(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("tiger"));
//		m.setName("관리자");
//		m.setRole("admin");
//		DBManager.insertMember(m);
//		System.out.println("관리자 계정 생성함");
		SpringApplication.run(ScurityBookApplication.class, args);
	}

}
