package com.jdc.demo.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import com.jdc.demo.dto.Register;

@Aspect
@Configuration
public class MyAspect {

	
//	@Pointcut("bean(myService)")
//	void myServiceBean() {
//		
//	}
	
//	@Before(value = "bean(myService) && args(str, a)", argNames = ("str, a"))
//	void beforeLog(String name, int z) {
//		System.out.println("Before Execution..." + name + z);
//	}
	
	@Before(value = "bean(myService) && execution(* hello(*, *))")
	void beforeLog(JoinPoint jointPoint) {
		Object[] args = jointPoint.getArgs();
		System.out.println("Before Execution..." + jointPoint + Arrays.toString(args));
	}
	
	@AfterReturning(pointcut = "bean(myService) && execution(com.jdc.demo..Register *(..)) && args(name, age)", 
					argNames = "result, name, age",
					returning = "result")
	void afterLog(Register result, String name, int age) {
		System.out.println("After Execution..." + name + age);
		System.out.println("Register, " + result);
	}
	
	@AfterThrowing(pointcut = "bean(myService) && execution(int divide(..)) && args(a, b)", 
				   argNames = "ex, a, b",
				   throwing = "ex")
	void afterThrow(RuntimeException ex, int a, int b) {
		System.out.println("After Throwing..." + a + b);
		System.out.println(ex.getClass().getSimpleName());
		System.out.println(ex.getMessage());
	}
	
	@After("bean(myService)")
	void after() {
		System.out.println("Finally...");
	}
	
//	@Around("bean(myService)")
//	Object aroundInvocation(ProceedingJoinPoint joinPoint) {
//		Object result = null;
//		
//		try {
//			System.out.println("Around Before...");
//			result = joinPoint.proceed();
//			System.out.println("Around After Execution...");
//		} catch (Throwable e) {
//			System.out.println("Around After Throwing...");
//		} finally {
//			System.out.println("Around Finally...");
//		}
//		return result;
//	}
}
