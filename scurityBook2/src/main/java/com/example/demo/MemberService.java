package com.example.demo;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO m = dao.findByID(username);

		if (m == null) {
			throw new UsernameNotFoundException(username);
		}

		UserDetails details = null;

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
