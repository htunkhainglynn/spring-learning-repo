package com.jdc.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.demo.MyService;
import com.jdc.demo.aspect.OtherServiceInterface;

@SpringJUnitConfig(locations = "classpath:context.xml")
public class ServiceTest {
	
	@Autowired
	private MyService myService;

	@Test
	void test() {
		myService.doWork();	
		if (myService instanceof OtherServiceInterface other) {
			other.doOther();
		}
	}
}
