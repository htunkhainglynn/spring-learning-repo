package com.jdc.demo.aspects;

import org.aspectj.lang.JoinPoint;

public class DemoAspect {

	public void doBefore(JoinPoint joinPoint) {
//		Object[] args = joinPoint.getArgs();
//		String a = null;
//		for (Object arg : args) {
//			 a = (String) arg;
//		  }
		System.out.println("do before with args..." );
	}
}
