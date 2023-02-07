package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.demo.config.AppConfig;

@SpringJUnitConfig(classes = AppConfig.class)
@TestMethodOrder(OrderAnnotation.class)
@PropertySource("sql.properties")
public class StatementTest {
	
	@Autowired
	JdbcOperations dbOperation;

	@Test
	@Order(1)
	@DisplayName("test PreparedStatementCreator psc, PreparedStatementCallback<T> action")
	@Sql(scripts = "/database.sql")
	void test(@Value("${member.insert}") String sql) {
		PreparedStatementCreator creator = conn -> {
			var stmt = conn.prepareStatement(sql);
			stmt.setString(1, "admin");
			stmt.setString(2, "123");
			stmt.setString(3, "hkl");
			stmt.setString(4, "1234");
			stmt.setString(5, "admin@gmail.com");
			return stmt;
		};
		
		int count = dbOperation.execute(creator, s -> s.executeUpdate());
		assertEquals(1, count);
	}
}
