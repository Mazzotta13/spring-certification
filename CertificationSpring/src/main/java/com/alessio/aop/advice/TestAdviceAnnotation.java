package com.alessio.aop.advice;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * @author ale
 * using an advice for a class annotateted withAnnotationLoggers
 */
@Aspect
@Component
public class TestAdviceAnnotation {
	@Pointcut("@annotation(com.alessio.aop.annotation.AnnotationLoggers)")
	public void AnnotationLoggersPointcut() {}
	
	@After("AnnotationLoggersPointcut()")
	public void afterAnnotationTarget() {
		System.out.println("TestAdviceAnnotation, afterAnnotationTarget()");
	}
}
