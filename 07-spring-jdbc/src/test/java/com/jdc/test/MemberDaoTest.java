package com.jdc.test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.demo.config.AppConfig;
import com.jdc.demo.dao.MemberDao;
import com.jdc.demo.dto.Member;

@TestMethodOrder(OrderAnnotation.class)
@SpringJUnitConfig(classes = AppConfig.class)
public class MemberDaoTest {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private JdbcOperations dbOperation;
	
	
	@Test
	@Order(1)
	@Sql(scripts = "/database.sql")
	void test() {
		Member m = new Member();
		m.setLoginId("admin");
		m.setPassword("123");
		m.setName("hkl");
		
		memberDao.create(m);
	}
	
	@Test
	@Order(2)
	void testConn() {
		var data = dbOperation.execute((Connection conn) -> {
			var stmt = conn.createStatement();
			var rs = stmt.executeQuery("select count(*) from MEMBER");
			while (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		});
		
		assertEquals(data, 1);
	}
	
	@Test
	@Order(3)
	void testStmt() {
		var data = dbOperation.execute((Statement stmt) -> {
			return stmt.executeUpdate("""
					INSERT INTO MEMBER (loginId, password, name) VALUES 
					("member", "234", "mama")
					""");
		});	
		assertEquals(data, 1);
	}
	
	@Test
	@Order(4)
	void testStaticQueryResultSetExtractor() {
		var result = dbOperation.query("select count(*) from MEMBER", rs -> {
			while(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		});
		
		assertEquals(result, 2);
	}
	
	@Test
	@Order(5)
	void testStaticQueryRowCallbackHandler() {  // next() is done implicitly
		var list = new ArrayList<Member>();
		dbOperation.query("select * from MEMBER", rs -> {  // no return 
			Member m = new Member();
			m.setLoginId(rs.getString("loginId"));
			m.setPassword(rs.getString("password"));
			m.setName(rs.getString("name"));
			list.add(m);
		});
		
		assertEquals(list.size(), 2);
	}
	
	@Test
	@Order(6)
	void testStaticQueryRowMapper() {  // next() is done implicitly
																	  // ResultSet rs, int rowNum
		List<Member> list = dbOperation.query("select * from MEMBER", (rs, n) -> {  
			Member m = new Member();
			m.setLoginId(rs.getString("loginId"));
			m.setPassword(rs.getString("password"));
			m.setName(rs.getString("name"));
			return m;
		});
		
		assertEquals(list.size(), 2);
	}
	
	
	@Test
	@Order(7)
	
	void testQueryForObject() {  // for single row single column
		var count = dbOperation.queryForObject("select count(*) from member", Integer.class);
		assertEquals(2, count);
		
		var name = dbOperation.queryForObject("select name from member where loginId = 'admin'", String.class);
		assertEquals("hkl", name);
	}
}
