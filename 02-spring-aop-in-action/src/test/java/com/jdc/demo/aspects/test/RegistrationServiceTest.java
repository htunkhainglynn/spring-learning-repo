package com.jdc.demo.aspects.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


import com.jdc.demo.services.RegistrationService;


@SpringJUnitConfig(locations = "classpath:context.xml")
public class RegistrationServiceTest {

	@Autowired
	private RegistrationService registrationService;
	
	@Test
	void test() {
		registrationService.register(null, null, null);
	}
}
