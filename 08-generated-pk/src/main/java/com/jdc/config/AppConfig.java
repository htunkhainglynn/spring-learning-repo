package com.jdc.config;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@PropertySource("sql.properties")	
@ComponentScan("com.jdc.product.model.dao")
public class AppConfig {

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		builder.setType(EmbeddedDatabaseType.H2);
		builder.setName("dataSource");
		builder.addScript("classpath:/database.sql");
		return builder.build();
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public SimpleJdbcInsert categoryInsert(DataSource dataSource) {  // do not need sql.properties explicitly
		var insert = new SimpleJdbcInsert(dataSource);
		insert.setTableName("category");
		insert.setGeneratedKeyNames("id");
		return insert;
	}
	
	@Bean
	@Qualifier("update")
	public PreparedStatementCreatorFactory categoryUpdate(@Value("${update.category}") String sql) {
		return new PreparedStatementCreatorFactory(sql, new int[] {
				Types.VARCHAR,
				Types.INTEGER
		});
	}

	
	@Bean
	@Qualifier("delete")
	public PreparedStatementCreatorFactory delete(@Value("${delete}") String sql) {
		return new PreparedStatementCreatorFactory(sql, new int[] {
				Types.INTEGER
		});
	}
}
