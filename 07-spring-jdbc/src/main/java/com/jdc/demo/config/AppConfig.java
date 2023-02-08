package com.jdc.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

@Configuration
@PropertySource("/database.properties")
@ComponentScan("com.jdc.demo.dao")
@ImportResource("context.xml")
public class AppConfig {
	
	@Value("${db.url}")
	private String url;
	@Value("${db.user}")
	private String user;
	@Value("${db.password}")
	private String password;

	@Bean
	public DataSource dataSource() {
		
		// using MysqlConnectionPoolDataSource
		var ds = new MysqlConnectionPoolDataSource();
		ds.setUrl(url);
		ds.setUser(user);
		ds.setPassword(password);
		return ds;
		
		
		
		// using open source datasource library
//		var ds = new BoneCPConfig();
//		ds.setJdbcUrl(url);
//		ds.setUser(user);
//		ds.setPassword(password);
//		
//		return new BoneCPDataSource(ds);
	}
	
	@Bean
	public JdbcTemplate template(DataSource dataSource) {
		return new JdbcTemplate(dataSource, true);
	}
	
	@Bean
	@Qualifier("pscFactory")
	public PreparedStatementCreatorFactory pscFactory() {
		return null;
	}
}
