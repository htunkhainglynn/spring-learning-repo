package com.jdc.demo.services;

import org.springframework.stereotype.Component;

import com.jdc.demo.dto.Register;

@Component
public class MyService {

	public Register hello(String str, int num) {
		System.out.println("hello, myservice...");
		return new Register(str, num);
	}
	
	public int divide(int a, int b) {
		return a / b;
	}
}
