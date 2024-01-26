package com.example.demo.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.oracle.wls.shaded.org.apache.xalan.xsltc.compiler.sym;

@Aspect
@Component // 자동으로 스캔
public class DaoComm {
	
	// excution (public * com.example.demo.dao..*()) -> 반환값이 무엇이라도
	// excution (public List com.example.demo.dao..*()) -> 반환값이 List인 것만	
	// excution (public * com.example.demo.dao..*()) -> 매개변수가 없는 것만	
	// excution (public * com.example.demo.dao..*(int)) -> 매개변수가 1개이고 int인 것만	
	// excution (public * com.example.demo.dao..*(int, *)) -> 매개변수가 2개이고 int와 아무거나	
	// excution (public * com.example.demo.dao..*(..)) -> 매개변수가 없거나 여러개 이거나	
	@Pointcut("execution (public * com.example.demo.dao..*(..))")
	public void pro() {}
	
//	// Around는 ProceedingJoinPoint joinPoint를 가져야한다
//	@Around("pro()")
//	public Object around(ProceedingJoinPoint joinPoint) {
//		// 타겟이 동작하기 전 코드
//		String methodName = joinPoint.getSignature().toShortString();
//		System.out.println(methodName + "이 동작 전");
//		Object re = null;
//		long start = System.currentTimeMillis();
//		try {
//			// 타겟을 동작시키는 명령어
//			// object를 반환한다
//			re = joinPoint.proceed();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		// 타겟이 동작후 코드
//		System.out.println(methodName + "이 동작 후");
//		long end = System.currentTimeMillis();
//		System.out.println(end - start);
//		
//		
//		return re;
//	}
	
//	// 정상작동 되거나 오류나면 작동
//	@After("pro()")
//	public void after(JoinPoint joinPoint) {
//		String methodName = joinPoint.getSignature().toShortString();
//		System.out.println(methodName + "완료");
//	}
	
//	@AfterThrowing("pro()")
//	public void afterThrowing(JoinPoint joinPoint) {
//		String mehtodName = joinPoint.getSignature().toShortString();
//		System.out.println(mehtodName + "동작중 문제 발생");
//	}
	
//	@AfterReturning("pro()")
//	public void afterReturn(JoinPoint joinpoint) {
//		String methodName = joinpoint.getSignature().toShortString();
//		String methodName2 = joinpoint.getSignature().toLongString();
//		System.out.println(methodName);
//		System.out.println(methodName2);
//		System.out.println("타깃 메소드 정상완료");
//	}
	
	@Before("pro()")
	public void before() {
		System.out.println("pro 동작함");
	}
}
