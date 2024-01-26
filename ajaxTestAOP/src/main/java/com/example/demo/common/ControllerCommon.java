package com.example.demo.common;

import java.io.FileWriter;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.AopDAO;
import com.example.demo.vo.AopVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Component
@Aspect
@Setter
public class ControllerCommon {
	
	@Autowired
	private AopDAO dao;
	
	@Pointcut("execution (public * com.example.demo.controller..*(..))")
	public void Controller() {}
	
	@Around("Controller()")
	public Object fileLog(ProceedingJoinPoint joinPoint) {
		Object re = null;
		Object[] args = joinPoint.getArgs();
		HttpServletRequest req = (HttpServletRequest)args[0];
		System.out.println("args : " + req);
		
		String uri = req.getRequestURI();
		String ip = req.getRemoteAddr();
		long start = System.currentTimeMillis();
		Date date = new Date();
		String time = date.toLocaleString();
		String methodName = joinPoint.getSignature().toShortString();
		
		try {
			re = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		long delay = end - start;
		
		String row = uri+"/"+ip+"/"+time+"/"+delay+"\n";
		System.out.println(row);
		String fname= "c:/sist_log2/";
		try {
			int year = date.getYear() + 1900;
			int month = date.getMonth() + 1;
			int day = date.getDate();
			
			fname += year;
			if (month < 10) {
				fname += "0";
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
		
		AopVO a = new AopVO(0, uri, ip, methodName, time, delay);
		dao.insertAop(a);
		
		return re;
	}
}
