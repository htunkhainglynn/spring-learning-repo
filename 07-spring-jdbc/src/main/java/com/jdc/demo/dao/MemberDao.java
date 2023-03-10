package com.jdc.demo.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.jdc.demo.dto.Member;

@Component
public class MemberDao {

	@Autowired
	private JdbcTemplate template;
	
	public void create(Member member) {
		template.update("insert into member values (?, ?, ?, ?, ?)",
				member.getLoginId(),
				member.getPassword(),
				member.getName(),
				member.getPhone(),
				member.getEmail());
	}  
	
	
}
