package com.jdc.product.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.jdc.product.model.dto.Category;

@Component
public class CategoryDao2 {
	
	@Autowired
	SimpleJdbcInsert jdbc;
	
	BeanPropertyRowMapper<Category> rowMapper;
	
	public CategoryDao2() {
		rowMapper = new BeanPropertyRowMapper<Category>(Category.class);
	}

	@Value("${insert.category.name}")
	String insertSql;
	@Value("${update.category}")
	String updateSql;
	@Value("${search.by.id}")
	String findByIdSql;
	@Value("${search.count}")
	String findCountSql;
	@Value("${search.by.name}")
	String FindByNameSql;
	@Value("${delete}")
	String deleteSql;

	public int create(Category c) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", c.getName());
		return jdbc.executeAndReturnKey(params).intValue() ;
	}

	public int update(Category c) {
		return jdbc.getJdbcTemplate().update(updateSql, c.getName().toLowerCase(), c.getId());
	}


	public Category findById(int id) {
		return jdbc.getJdbcTemplate().queryForObject(findByIdSql, rowMapper, id);
	}

	public List<Category> findByName(String name) {
		return jdbc.getJdbcTemplate().query(FindByNameSql, rowMapper, name.toLowerCase().concat("%"));
	}
	
	public int findCountByName(String name) {
		return jdbc.getJdbcTemplate().queryForObject(findCountSql, Integer.class, name.toLowerCase().concat("%"));
	}

	public int delete(int id) {
		return jdbc.getJdbcTemplate().update(deleteSql, id);
	}
}
