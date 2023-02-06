package com.jdc.aspectj.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.demo.config.AppConfig;
import com.jdc.demo.services.MyService;

@SpringJUnitConfig(classes = AppConfig.class)
public class MyServiceTest {
	
	@Autowired
	private MyService myService;
	
	@Test
	void test() {
		myService.hello("Aspectj", 10);
		myService.divide(10, 0);
	}
}
