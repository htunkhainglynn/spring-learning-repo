package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.PreparedStatement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.demo.config.AppConfig;
import com.jdc.demo.dao.MemberRowMap;
import com.jdc.demo.dto.Member;

@SpringJUnitConfig(classes = AppConfig.class)
@TestMethodOrder(OrderAnnotation.class)
public class StatementTest2 {
	
	@Autowired
	JdbcOperations jdbc;

	@Autowired
	MemberRowMap rowMapper;
	
	@Test
	@Sql(scripts = "/database.sql")
	@Order(1)
	@DisplayName("Execute with simple sql string")
	void test1(@Value("${member.insert}") String sql) {
		var count = jdbc.execute(sql, (PreparedStatement stmt)-> {  // need to type cast because there are 2 execute()
				stmt.setString(1, "admin");
				stmt.setString(2, "admin");
				stmt.setString(3, "hkl");
				stmt.setString(4, "123");
				stmt.setString(5, "admin@");
				return stmt.executeUpdate();  // executeUpdate() returns int
		});
		
		assertEquals(1, count);
	}
	
	@Test
	@Order(2)
	@DisplayName("Update with PreparedStatementSetter")
	void test2(@Value("${member.insert}") String sql) {
		var count = jdbc.update(sql, stmt -> {  // don't need to be type casted and returns int
				stmt.setString(1, "member");
				stmt.setString(2, "admin");
				stmt.setString(3, "slwy");
				stmt.setString(4, "123");
				stmt.setString(5, "admin@");
		});
		
		assertEquals(1, count);
	}
	
	@Test
	@Order(3)  // the best one
	@DisplayName("Update with just using parameters")
	void test3(@Value("${member.insert}") String sql) {
		var count = jdbc.update(sql, "member2", "member", "hs", "234", "ss@");
		assertEquals(1, count);
	}
	
	/*
	 * Every query returns list.
	 */
	
	@Test
	@Order(4)
	@DisplayName("Extract with PreparedStatementSetter")
	void test4(@Value("${member.search.name}") String sql) {
		var member = jdbc.query(sql, stmt -> stmt.setString(1, "hkl"), (rs, n) -> {  // can use my custom rowMap just by importing
				Member m = new Member();
				m.setLoginId(rs.getString(1));
				m.setPassword(rs.getString(2));
				m.setName(rs.getString(3));
				m.setPhone(rs.getString(4));
				m.setEmail(rs.getString(5));
				return m;
			});

		assertEquals(1, member.size());
	}
	
	@Test
	@Order(5)
	@DisplayName("Extract with custome rowMapper")
	void test5(@Value("${member.search.name}") String sql) {
		var member = jdbc.query(sql, rowMapper, "hkl");  // return list 
		assertEquals(1, member.size());
	}
	
	@Test
	@Order(6)
	@DisplayName("Extract PK with query (not a good idea)")
	// <T> T query(String sql, @Nullable PreparedStatementSetter pss, ResultSetExtractor<T> rse)
	void test7(@Value("${member.search.pk}") String sql) {
		var member = jdbc.query(sql, stmt -> stmt.setString(1, "admin"), rs -> {
			if (rs.next()) {
				return rowMapper.mapRow(rs, 1);  // return just member object
			}
			return null;
		});
		assertEquals("hkl", member.getName());
	}
	
	@Test
	@Order(7)
	@DisplayName("Extract PK with query using extractor (not a good idea)")
	// <T> T query(String sql, ResultSetExtractor<T> rse, @Nullable Object... args)
	void test8(@Value("${member.search.pk}") String sql) {
		ResultSetExtractor<Member> extractor = rs -> {
			if (rs.next()) {
				return rowMapper.mapRow(rs, 1);
			}
			return null;
		};
		
		var member = jdbc.query(sql, extractor, "admin");
		assertEquals("hkl", member.getName());
	}
	
	@Test
	@Order(8)
	@DisplayName("Extract PK with queryObject (good idea)")
	void test9(@Value("${member.search.pk}") String sql) {
		var member = jdbc.queryForObject(sql, rowMapper, "admin");
		assertEquals("hkl", member.getName());
	}
	
	@Test
	@Order(9)
	@DisplayName("Extract One row one colume eg (count(*)) with queryObject (good idea)")
	void test10() {
		var sql = "select count(*) from member where loginId = ?";
		var count = jdbc.queryForObject(sql, Long.class, "admin");  // Integer.class Ok
		assertEquals(1, count);
	}
}
