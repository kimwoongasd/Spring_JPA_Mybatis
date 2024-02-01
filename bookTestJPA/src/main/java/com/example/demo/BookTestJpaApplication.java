package com.example.demo;

import java.lang.reflect.Method;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookTestJpaApplication {

	public static void main(String[] args) {
		
		// 문자열로 된 메소드 이름을 가지고 해당 메소드를 동작 시키기
		String methodname = "sub";
		Test test = new Test();
		int r = 0;
		try {
			Class<?> cls = Class.forName(test.getClass().getName());
			
			Method method = cls.getDeclaredMethod(methodname, Integer.class);
			// invoke : object를 반환
			r = (Integer)method.invoke(test, 5);
			System.out.println(r);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		SpringApplication.run(BookTestJpaApplication.class, args);
	}

}
