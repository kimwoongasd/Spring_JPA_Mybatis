package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Member;

import lombok.Setter;

@Service
@Setter
public class MemberService implements UserDetailsService {
	
	@Autowired
	private MemberDAO dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
//		dao.save(new Member("kim", "유재석", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("1234"), "user" , null));
//		dao.save(new Member("lee", "이순신", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("1234"), "user" , null));
//		dao.save(new Member("kang", "강호동", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("1234"), "user" , null));
//		dao.save(new Member("sist01","관리자1",PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("admin"), "admin", null));
//		dao.save(new Member("sist02","관리자2",
//				PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("admin"), "admin", null));
		
		Member m = null;
		Optional<Member> o = dao.findById(username);
		if (o.isPresent()) {
			m = o.get();
		}
		UserDetails details = null;
		
		UserBuilder builder = User.builder();
		builder.username(m.getId());
		builder.password(m.getPwd());
		builder.roles(m.getRole());
		details = builder.build();
		
		return details;
	}
	
	
	public List<Member> findAll(){
		return dao.findAll();
	}
	
	public void delete(String id) {
		dao.deleteById(id);
	}
	
	public Member findById(String id) {
		return dao.findById(id).get();
	}
}
