package com.alessio.aop.advice;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class TestAdvice {

	@Pointcut("execution(* com.alessio.aop.component.TestService.*(..))")
    public void testService() {};
    
	@Before("testService()")
	public void beforeAdvice() {
		System.out.println("TestAdvice, beforeAdvice()");
	}
	
	@Before(value = "execution(* com.alessio.aop.component.TestService.*(..))")
	public void beforeAdvice2() {
		System.out.println("TestAdvice, beforeAdvice2()");
	}
	
	@AfterReturning(pointcut = "execution(* com.alessio.aop.component.TestService.*(..))")
	public void afterReturning() {
		System.out.println("TestAdvice, AfterReturning()");
	}
	
	@After(value = "execution(* com.alessio.aop.component.TestService.*(..))")
	public void after() {
		System.out.println("TestAdvice, after()");
	}
	
	@AfterThrowing(value = "execution(* com.alessio.aop.component.TestService.*(..))")
	public void afterThrowing() {
		System.out.println("TestAdvice, afterThrowing()");
	}
	
}
