package com.jdc.demo.admin.dao;

import org.springframework.stereotype.Component;

import com.jdc.demo.aspects.DemoAnnotation;

@Component
@DemoAnnotation
public class AdminDao {

	public void searchAdmin() {
		System.out.println("Searching admin");
	}
}
