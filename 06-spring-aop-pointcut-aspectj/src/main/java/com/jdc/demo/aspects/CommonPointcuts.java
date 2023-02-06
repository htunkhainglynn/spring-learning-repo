package com.jdc.demo.aspects;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonPointcuts {

	@Pointcut("execution(* search*(..))")
	public void searchMethods() {}
	
	@Pointcut("within(com.jdc.demo..member.dao.*)")
	public void searchMembersOnly() {}
	
	@Pointcut("@within(com.jdc.demo.aspects.DemoAnnotation)")
	public void demoAnnotationClass() {}
}
