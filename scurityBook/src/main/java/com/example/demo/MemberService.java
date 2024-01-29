package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.MemberVO;

import lombok.Setter;

@Service
@Setter
public class MemberService implements UserDetailsService {	

	@Autowired
	private MemberDAO dao;
	
	// 유저의 정보를 불러와서 UserDetails로 리턴
	// https://programmer93.tistory.com/68
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO m = dao.findByID(username);
		if (m == null) {
			throw new UsernameNotFoundException(username);
		}
		
		// UserDetails : Spring Security에서 사용자의 정보를 담는 인터페이스이다
		UserDetails details = null;
		
		// 빌더 패턴(Builder Pattern)의 장점
		// 필요한 데이터만 설정할 수 있음
//		유연성을 확보할 수 있음
//		가독성을 높일 수 있음
//		변경 가능성을 최소화할 수 있음
		UserBuilder builder = User.builder();
		builder.username(m.getId());
		builder.password(m.getPwd());
		builder.roles(m.getRole());
		details = builder.build();
		
		System.out.println("loadUserByUsername 동작");
		System.out.println("회원 아이디 : " + username);
		
		return details;
	}
}
