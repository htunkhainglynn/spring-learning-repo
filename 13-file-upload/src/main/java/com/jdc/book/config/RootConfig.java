package com.jdc.book.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

@Configuration
@ComponentScan("com.jdc.book.root")
public class RootConfig {

	@Bean
	public DataSource datasource() {
		var ds = new BasicDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/books_db");
		ds.setUsername("root");
		ds.setPassword("admin");
		return ds;
	}
	
	@Bean
	public NamedParameterJdbcTemplate namedTemplate(DataSource ds) {
		return new NamedParameterJdbcTemplate(ds);
	}
	
	@Bean 
	public JdbcTemplate jdbcTemplate(DataSource ds) {
		return new JdbcTemplate(ds);
	}
	
	@Bean 
	@Scope("prototype")  // want to use separate object, if singleton it will create same objects
	public SimpleJdbcInsert bookInsert(DataSource ds) {
		return new SimpleJdbcInsert(ds);
	}
	
//	@Bean 
//	@Scope("prototype")  // want to use separate object, if singleton it will create same objects
//	public SimpleJdbcInsert categoryInsert(DataSource ds) {
//		var insert = new SimpleJdbcInsert(ds);
//		insert.setTableName("category");
//		insert.setGeneratedKeyNames("id");
//		return insert;
//	}
}
