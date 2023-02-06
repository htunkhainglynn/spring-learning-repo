package com.jdc.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MyAspect {
	/*
	@Before("searchMethods()")
	void beforeLog(JoinPoint joinPoint) {
		System.out.println("before execution...");
		System.out.println("---------------------------");
		System.out.printf("%-16s : %s%n".formatted("Target Class", joinPoint.getTarget().getClass().getSimpleName()));
		System.out.printf("%-16s : %s%n".formatted("Target Method", joinPoint.getSignature().getName()));
		System.out.println("---------------------------");
	} */
	
	/*
	There is something aspectJ can do that schema based can.
	We can combine point cut in aspectJ style.
	@Before("searchMethods() and searchMembersOnly()")
	This will do just methods that start with search only in member.dao package.
	Point cut can be written in another file in the same package.
	You can invoke like this @Before("CommonPointcuts.searchMethods() and CommonPointcuts.searchMembersOnly()".
	They are in CommonPointcuts.java.
	*/
	
	
	@Before("CommonPointcuts.searchMethods() and CommonPointcuts.searchMembersOnly()")  //composing point cut references
	void beforeLog(JoinPoint joinPoint) {
		System.out.println("before execution...");
		System.out.println("---------------------------");
		System.out.printf("%-16s : %s%n".formatted("Target Class", joinPoint.getTarget().getClass().getSimpleName()));
		System.out.printf("%-16s : %s%n".formatted("Target Method", joinPoint.getSignature().getName()));
		System.out.println("---------------------------");
	}
}
