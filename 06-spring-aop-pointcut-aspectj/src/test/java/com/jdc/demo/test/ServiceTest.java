package com.jdc.demo.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.demo.AppConfig;
import com.jdc.demo.admin.dao.AdminDao;
import com.jdc.demo.member.dao.MemberDao;

@SpringJUnitConfig(classes = AppConfig.class)
public class ServiceTest {
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Test
	void test() {
		adminDao.searchAdmin();
		adminDao.inertAdmin();
		memberDao.searchMember();
	}
}
