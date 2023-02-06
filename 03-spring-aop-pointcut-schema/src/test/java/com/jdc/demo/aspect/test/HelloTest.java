package com.jdc.demo.aspect.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.demo.admin.service.AdminService;

@SpringJUnitConfig(locations = "classpath:context.xml")
public class HelloTest {
	
	@Autowired
	private AdminService adminService;  // you can use service by tagging annotation on particular service
	
	@Test
	void test() {
		adminService.doWork("lazy");
		adminService.doJob(1, 2);
	}
}

