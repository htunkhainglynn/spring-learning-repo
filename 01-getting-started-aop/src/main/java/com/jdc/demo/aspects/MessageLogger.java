package com.jdc.demo.aspects;

public class MessageLogger {

	public void doBefore() {
		System.out.println("before business method invocation"); 
	}
	
	public void doAfter() {
		System.out.println("after business method invocation");
	}
	
}
