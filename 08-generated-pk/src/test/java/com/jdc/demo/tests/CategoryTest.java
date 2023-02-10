package com.jdc.demo.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.config.AppConfig;
import com.jdc.product.model.dao.CategoryDao;
import com.jdc.product.model.dto.Category;

@SpringJUnitConfig(classes = AppConfig.class)
@TestMethodOrder(OrderAnnotation.class)
public class CategoryTest {
	
	@Autowired
	private CategoryDao dao;

	@Test
	void test1() {
		Category c1 = new Category();
		c1.setName("food");
		Category c2 = new Category();
		c2.setName("drink");
		int gk = dao.create(c1);
		int gk2 = dao.create(c2);
		assertEquals(2, gk2);
	}
}
