package com.jdc.product.model.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
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
