package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
	
	@GetMapping("/listBook")
	public void list(HttpSession session) {
		// 인증된 회원의 정보를 갖고 오기 위하여 먼저 시큐리티의 인증객체가 필요
		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();
		
		
		// 이 인증객체를 통해서 인증된 User객체를 받아온다
		User user = (User)authentication.getPrincipal();
		
		// 이 인증된 User를 통해서 로그인한 회원의 아이디를 가져온다
		String id = user.getUsername();
		
		// 이 정보를 세션에 상태유지한다
		// 만약 id뿐 아니라 로그인한 회원의 다른 정보도 필요하다면
		// dao를 통해 회원 정보를 갖고와서 상태유지한다
		session.setAttribute("id", id);
		
	}
}
