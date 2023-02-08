package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.demo.config.AppConfig;
import com.jdc.demo.dto.Member;


@SpringJUnitConfig(classes = AppConfig.class)
@TestMethodOrder(OrderAnnotation.class)
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
	
	@Test
	@Order(2)
	@DisplayName("test PreparedStatementCreatorFactory(String sql, int... types)")
	void test1(@Qualifier("insertFactory") PreparedStatementCreatorFactory factory) {  // dependency injection
		var creator = factory.newPreparedStatementCreator(List.of(
					"member", "member", "hkl", "123", "aaa"
				));
		
		int count = dbOperation.update(creator);
		assertEquals(1, count);
	}
	
	@Test
	@Order(3)
	@DisplayName("test select with PreparedStatementCreatorFactory(String sql, int... types)")
	void test2(@Qualifier("searchAdminFactory") PreparedStatementCreatorFactory factory) {
		var creator = factory.newPreparedStatementCreator(List.of("admin"));
		
		List<Member> list = dbOperation.query(creator, (rs, n) -> {  // check in MemberDaoTest
			Member m = new Member();
			m.setLoginId(rs.getString("loginId"));
			m.setPassword(rs.getString("password"));
			m.setName(rs.getString("name"));
			m.setPhone(rs.getString("phone"));
			m.setEmail(rs.getString("email"));
			return m;
		});
		
		System.out.println(list);
		
	}
}





















