package com.alessio.aop.component;

import org.springframework.stereotype.Service;

import com.alessio.aop.annotation.AnnotationLoggers;

@Service
public class TestServiceAnnotations {
	
	@AnnotationLoggers
	public void test() {
		System.out.println("TestServiceAnnotations, test()");
	}
}
