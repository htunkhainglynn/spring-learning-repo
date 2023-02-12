package com.jdc.demo.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.config.ProductConfig;
import com.jdc.product.model.dao.CategoryDao2;
import com.jdc.product.model.dao.ProductDao;
import com.jdc.product.model.dto.Product;

@SpringJUnitConfig(classes = ProductConfig.class)  // need to build categoryInsert to auto wire categoryDao2
@TestMethodOrder(OrderAnnotation.class)
public class ProductDaoTest {

	@Autowired
	private ProductDao dao;
	
	@Autowired
	private CategoryDao2 categories;
	
	@Test
	@Order(1)
	@DisplayName("1. Insert")
	@Sql(statements = {
			"insert into category (name) values ('Foods')",
			"insert into category (name) values ('Drinks')"
	})
	void test1() {
		var category = categories.findById(1);
		Product p = new Product();
		p.setCategory(category);
		p.setName("Cake");
		p.setPrice(600);
		int id = dao.create(p);
		assertEquals(1, id);
	}
	
	@Test
	@Order(2)
	@DisplayName("2. Find by product id")
	void test2() {
		Product p = dao.findById(1);
		assertEquals(p.getName(), "Cake");
		assertEquals(p.getCategory().getName(), "Foods");
	}
	
	@Test
	@Order(3)
	@DisplayName("3. Update")
	void test3() {
		Product p = new Product();
		p.setId(1);
		p.setName("Pucci");
		p.setPrice(800);
		int count = dao.update(p);
		assertEquals(1, count);
		Product p1 = dao.findById(1);
		assertEquals(p1.getName(), "Pucci");
	}
	
	
	@Test
	@Order(4)
	@DisplayName("4. Find by Name Like")
	void test4() {
		List<Product> list = dao.findByName("pucci");
		assertEquals(list.size(), 1);
	}
	
	@Test
	@Order(5)
	@DisplayName("5. delete")
	void test5() {
		int count = dao.delete(1);
		assertEquals(1, count);
		assertNull(dao.findById(1));
	}
	
	@Test
	@Order(6)
	@DisplayName("6. Find by category Id")
	void test7() {
		List<Product> list = dao.findByCategoryId(1);
		assertNotNull(list);
	}
}
