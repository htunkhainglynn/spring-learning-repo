package com.jdc.demo.dao;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdc.demo.custom.anno.CustomRowMapper;
import com.jdc.demo.dto.Member;

@CustomRowMapper
public class MemberRowMap implements RowMapper<Member> {

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member m = new Member();
		m.setLoginId(rs.getString("loginId"));
		m.setPassword(rs.getString("password"));
		m.setName(rs.getString("name"));
		m.setPhone(rs.getString("phone"));
		m.setEmail(rs.getString("email"));
		return m;
	}
	

}
