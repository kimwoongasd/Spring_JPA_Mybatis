package com.example.demo.common;

import java.io.FileWriter;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.http.HttpServletRequest;

@Component
@Aspect
public class ControllerCommon {
	
	@Pointcut("execution(public * com.example.demo.controller..*(..))")
	public void controller() {
		
	}
	
	@Around("controller()")
	public Object fileLog(ProceedingJoinPoint joinPoint) {
		Object re = null;
		// joinPoint을 통해서 모든 
		Object[] args = joinPoint.getArgs();
		HttpServletRequest req = (HttpServletRequest)args[0];
		
		String uri = req.getRequestURI();
		String ip = req.getRemoteAddr();
		long start = System.currentTimeMillis();
		Date date = new Date(start);
		String time = date.toLocaleString();
		
		try {
			re = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		long delay = end-start;
		
		String row = uri+"/"+ip+"/"+time+"/"+delay+"\n";
		System.out.println(row);
		
		try {
			int year = date.getYear() + 1900;
			int month = date.getMonth() + 1;
			int day = date.getDate();
			String fname= "c:/sist_log/";
			fname += year;
			if (month < 10) {
				fname+= "0";
			}
			fname += month;
			if (day < 10) {
				fname += "0";
			}
			
			fname += day;
			fname += ".txt";
			
			FileWriter fw = new FileWriter(fname, true);
			fw.write(row);
			fw.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return re;
	}
}
