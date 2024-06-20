package com.lec07.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component //인스턴스 초기화 : 메모리에 올려라!
@Aspect	   // 공통기능 :: AnnotationAwareAspectJAutoProxyCreator가 현재 클래스를 Proxy
public class CommonAspect {
	
	@Pointcut("execution(public * com.lec07..*DAO.*(..))")
	public void dummyDAOCut() {} // DAO는 인터페이스가 없어 CGLIB 방식으로 구동해야한다.
								 // pom.xml에 jar설치가 되면 
								//<aop:aspectj-autoproxy proxy-target-class="true" />
								//를 쓰지 않아도 구동가능
	
	@Pointcut("execution(public * com.lec07..*Impl.*(..))")
	public void dummyImplCut() {}
	
	@Before("dummyImplCut()")
	public void beforeAdvice() {
		System.out.println("\t 실행전CommonAspect.beforeAdvice()");
	}
	@After("dummyImplCut()")
	public void afterAdvice() {
		System.out.println("\t 실행 후 무조건CommonAspect.afterAdvice()");
	}
	@AfterThrowing(pointcut="dummyImplCut()",throwing="ex")
	public void afterThrowingAdvice(Exception ex) {
		System.out.println("\t 실행 후 에러CommonAspect.afterThrowingAdvice() :: " + ex.getMessage());
	}
	@AfterReturning(pointcut="dummyImplCut()",returning="res")
	public void afterReturningAdvice(Object res) {
		System.out.println("\t 실행 후 정상CommonAspect.afterReturningAdvice() :: " + res);
	}
	@Around("dummyImplCut()")
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
