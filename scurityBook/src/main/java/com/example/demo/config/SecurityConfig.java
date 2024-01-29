package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http);
		
		// 인증과 인과에 대한 설정
		http.authorizeHttpRequests()
		// permitAll : 설정한 url에대해 아무나 접근 가능
		// all, error 안은 누구나 접근 가능
		.mvcMatchers("/", "/join", "/all/**", "/error").permitAll()
		// admin이라는 role이 있어야 /admin/ 안에 접근 가능
		.mvcMatchers("/admin/**").hasRole("admin")
		// 그외 서비스는 인증을 거치면 사용가능
		.anyRequest().authenticated();
		
		http.formLogin().loginPage("/login").permitAll()
		.defaultSuccessUrl("/listBook"); //로그인 성공시 이동할 URL
		
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.invalidateHttpSession(true)
		.logoutSuccessUrl("/login");
		
		http.httpBasic();
		
	}
	
	
}
