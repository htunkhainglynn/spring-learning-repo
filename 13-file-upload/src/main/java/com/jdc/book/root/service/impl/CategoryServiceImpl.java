package com.jdc.book.root.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.jdc.book.root.dto.Category;
import com.jdc.book.root.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	NamedParameterJdbcTemplate template;
	
	BeanPropertyRowMapper<Category> categoryRowMapper;
	
	public CategoryServiceImpl() {
		categoryRowMapper = new BeanPropertyRowMapper<>(Category.class);
	}

	@Override
	public List<Category> getAllCategories() {
		return template.query("SELECT * FROM category ORDER BY name", 
				categoryRowMapper);
	}

	@Override
	public Optional<Category> findById(int id) {
		return template.getJdbcTemplate().queryForStream("SELECT * FROM category WHERE id = ?", 
				categoryRowMapper, id).findFirst();
	}
}
