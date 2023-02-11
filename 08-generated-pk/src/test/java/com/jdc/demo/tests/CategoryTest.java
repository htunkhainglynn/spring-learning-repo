package com.jdc.demo.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
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
	@Order(1)
	@DisplayName("1. Insert")
	void test1() {
		Category c = new Category();
		c.setName("Salad");
		Category c1 = new Category();
		c1.setName("food");
		dao.create(c);
		int gk2 = dao.create(c1);
		assertEquals(2, gk2);
	}
	
	@Test
	@Order(2)
	@DisplayName("2. Update")
	void test2() {
		Category c = new Category();
		c.setId(1);
		c.setName("Drink");
		int count = dao.update(c);
		assertEquals(1, count);
	}
	
	@Test
	@Order(3)
	@DisplayName("3. Find by id")
	void test3() {
		Category c = dao.findById(1);
		assertEquals("Drink", c.getName());
	}
	
	@Test
	@Order(4)
	@DisplayName("4. Find by Name Like")
	void test4() {
		List<Category> c = dao.findByName("Dri%");
		assertEquals(1, c.size());
	}
	
	@Test
	@Order(5)
	@DisplayName("5. delete")
	void test5() {
		int count = dao.delete(1);
		assertEquals(1, count);
	}
}
