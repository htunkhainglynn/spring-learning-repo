package com.jdc.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.demo.BusinessInterface;


@SpringJUnitConfig(locations = "classpath:context.xml")
public class AopDemoTest {
	
	@Autowired
	private BusinessInterface businessBean;  // must to equals id from context.xml
	
	@Autowired
	private BusinessInterface myBean;
	
	@Test
	void demo() {
		businessBean.doBusiness();
		myBean.doBusiness();
	}
			
}
