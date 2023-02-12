package com.jdc.product.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import com.jdc.product.model.dto.Product;

@Service
public class ProductDao {
	
	RowMapper<Product> rowMapper;
	
	public ProductDao() {
		rowMapper = new BeanPropertyRowMapper<Product>(Product.class);
	}

	@Autowired
	private NamedParameterJdbcOperations jdbc;
	
	@Value("${insert.product.name}")
	String insertSql;
	@Value("${product.update}")
	String updateSql;
	@Value("${product.findById}")
	String findByIdSql;
	@Value("${delete.product}")
	String deleteSql;
	@Value("${product.findByName}")
	String findByNameSql;
	@Value("${product.findByCategoryId}")
	String findByCategoryIdSql;
	
	public int create(Product p) {
		var key = new GeneratedKeyHolder();  // this is holder for generated primary key 
		var params = new MapSqlParameterSource();  // same with map just using to know
		params.addValue("name", p.getName());  // map use put
		params.addValue("category_id", p.getCategory().getId());
		params.addValue("price", p.getPrice());
		
		jdbc.update(insertSql, params, key);  // update(sql, mapSqlParameterSource, keyHolder)
		return key.getKey().intValue();
	}

	public int update(Product p) {
		var params = new MapSqlParameterSource();
		params.addValue("id", p.getId());
		params.addValue("name", p.getName());
		params.addValue("price", p.getPrice());
	    return jdbc.update(updateSql, params);
	}

	public Product findById(int id) {
		var params = new MapSqlParameterSource();
		params.addValue("id", id);
		return jdbc.queryForStream(findByIdSql, params, rowMapper).findFirst().orElse(null);  // just use it, learning stream
	}

	public int delete(int id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return jdbc.update(deleteSql, params);
	} 

	public List<Product> findByName(String keyword) {
		var params = new MapSqlParameterSource();
		params.addValue("keyword", keyword.concat("%"));
		return jdbc.query(findByNameSql, params, rowMapper);
	}

	public List<Product> findByCategoryId(int id) {
		var params = new MapSqlParameterSource();
		params.addValue("id", id);
		return jdbc.query(findByCategoryIdSql, params, rowMapper);
	}
	
	
}
