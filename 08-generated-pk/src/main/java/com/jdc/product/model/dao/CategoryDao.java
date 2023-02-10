package com.jdc.product.model.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.jdc.product.model.dto.Category;

@Service
public class CategoryDao {

	@Autowired
	private SimpleJdbcInsert insert;
	
	public int create(Category c) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("name", c.getName());
		
		return insert.executeAndReturnKey(params).intValue();
	}
}
