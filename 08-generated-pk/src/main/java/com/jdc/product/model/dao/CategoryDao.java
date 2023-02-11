package com.jdc.product.model.dao;

//import java.sql.Types;
import java.util.HashMap;
import java.util.List;
//import java.util.List;
import java.util.Map;

import javax.management.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.jdbc.core.JdbcOperations;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
//import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import com.jdc.product.model.dto.Category;

@Service
public class CategoryDao {

	@Autowired
	private SimpleJdbcInsert insert;
	
	@Autowired
	private JdbcOperations jdbc;
	
	@Autowired
	private PreparedStatementCreatorFactory categoryUpdate;
	
	@Autowired
	private CategoryRowMapper categoryRowMapper;
	
	@Autowired
	private PreparedStatementCreatorFactory delete;
	
	public int create(Category c) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("name", c.getName());
		
		return insert.executeAndReturnKey(params).intValue();
	}

	public int update(Category c) {	
		var stmt = categoryUpdate.newPreparedStatementCreator(List.of(c.getName(), c.getId()));
		int count = jdbc.update(stmt);
		return count;
	}

	@Value("${search.by.id}") String sqlId;
	public Category findById(int id) {
		Category c = jdbc.queryForObject(sqlId, categoryRowMapper, id);
		return c;
	}

	@Value("${search.by.name}") String sqlName;
	public List<Category> findByName(String name) {
		List<Category> c = jdbc.query(sqlName, categoryRowMapper, name);
		return c;
	}

	public int delete(int id) {
		PreparedStatementCreator stmt = delete.newPreparedStatementCreator(List.of(id));
		int count = jdbc.update(stmt);
		return count;
	}
	

//	@Autowired
//	JdbcOperations jdbc;
//	
//	// need to add sql.properties explicitly
//	@Value("${insert.category.name}")
//	private String sql;
//	
//	public int create(Category c) {
//		var create = new PreparedStatementCreatorFactory(sql, new int[] {
//				Types.VARCHAR
//		});
//		create.setReturnGeneratedKeys(true);
//		var stmt = create.newPreparedStatementCreator(List.of(c.getName()));
//		var keyHolder = new GeneratedKeyHolder();
//		jdbc.update(stmt, keyHolder);
//		
//		return keyHolder.getKey().intValue();
//	}
}
