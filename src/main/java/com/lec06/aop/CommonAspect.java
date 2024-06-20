package com.lec06.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class CommonAspect {
	public void beforeAdvice() {
		System.out.println("\t 실행전CommonAspect.beforeAdvice()");
	}
	public void afterAdvice() {
		System.out.println("\t 실행 후 무조건CommonAspect.afterAdvice()");
	}
	public void afterThrowingAdvice(Exception ex) {
		System.out.println("\t 실행 후 에러CommonAspect.afterThrowingAdvice() :: " + ex.getMessage());
	}
	public void afterReturningAdvice(Object res) {
		System.out.println("\t 실행 후 정상CommonAspect.afterReturningAdvice() :: " + res);
	}
	public void aroundAdvice(ProceedingJoinPoint jp) {
		try {
			System.out.println("\t 앞-CommonAspect.aroundAdvice()");
			System.out.println("\t  :: " + jp.getSignature());
			jp.proceed();    // aOPService.svcDelete();
			System.out.println("\t 뒤-CommonAspect.aroundAdvice()");
		}catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
}
