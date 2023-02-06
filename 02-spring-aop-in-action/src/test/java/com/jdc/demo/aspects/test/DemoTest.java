package com.jdc.demo.aspects.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.demo.services.DemoService;

@SpringJUnitConfig(locations = "classpath:context.xml")
public class DemoTest {
	
	@Autowired
	private DemoService demoService;
	
	@Test
	void test() {
		demoService.doWork("hello", "world");
		demoService.getStudent();
		demoService.divide(10, 0);
	}
}
