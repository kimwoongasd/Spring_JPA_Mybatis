package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	// 비밀번호를 암호화 하기 위해서 생성
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		.requestMatchers("/", "/member/login").permitAll()
		// /admin에서 role이 admin일 경우
		.requestMatchers("/admin/**").hasRole("admin")
		// 로그인 하면 모두 사용가능
		.anyRequest().authenticated();
		
		http.formLogin().loginPage("/member/login").permitAll()
		.defaultSuccessUrl("/board/list");
		
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.invalidateHttpSession(true)
		.logoutSuccessUrl("/member/login");
		
		http.httpBasic();
		
		return http.build();
	}
}
