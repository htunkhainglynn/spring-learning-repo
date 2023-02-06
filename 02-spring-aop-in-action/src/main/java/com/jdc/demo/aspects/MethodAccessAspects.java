package com.jdc.demo.aspects;

import javax.management.RuntimeErrorException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import com.jdc.demo.dto.Student;

public class MethodAccessAspects {

//	public void beforeExecution(JoinPoint joinPoint) {
//		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//	    String methodName = signature.getName();
//		Object[] args = joinPoint.getArgs();
//		
//		for (Object arg : args) {
//			System.out.println(arg);
//		}
//			
//		System.out.println("before running " + methodName + " with args " + args);
//	}
	
	public void beforeExecution(JoinPoint joinPoint, String ...args) {
		for (String arg : args) {
			System.out.println(arg);
		}
		System.out.println("before...");
	}

	public void afterExecution(JoinPoint joinPoint,Student student) {
		System.out.println("after returning...");
		System.out.println(student);
	}

	public void afterThrowing(JoinPoint joinPoint, RuntimeException exception) {	
		System.out.println("after throwing...");
		System.out.println(exception.getMessage());
	}

	public void afterFinally(JoinPoint joinPoint) {

	}
	
	public Object aroundInvocation(ProceedingJoinPoint joinPoint) {  // joinPoint is the method we want to modify
		
		Object result = null;
		
		try {
			
			// before joinPoint
			System.out.println("Before...");
			
			result = joinPoint.proceed();
			
			System.out.println("After...");
		} catch(Throwable e) {
			System.out.println("After throwing...");
			throw new RuntimeErrorException((Error) e);
		} finally {
			System.out.println("Finally...");
		}
		
		return result;
	}

}
