package com.jdc.demo.services;

import org.springframework.stereotype.Service;

import com.jdc.demo.dto.Student;

@Service
public class DemoService {

	public void doWork(String ... names) {
		System.out.println("Demo done...");
	}
	
	public Student getStudent() {
		return new Student(0, "koko", "koko@gmail.com");
	}
	
	public int divide(int a, int b) {
		return a / b;
	}
}
