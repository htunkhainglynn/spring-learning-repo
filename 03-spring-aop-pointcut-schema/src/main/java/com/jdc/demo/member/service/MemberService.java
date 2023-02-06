package com.jdc.demo.member.service;

import org.springframework.stereotype.Service;

import com.jdc.demo.HelloEnable;

@Service
public class MemberService implements HelloEnable {

	@Override
	public void doWork(String msg) {
		System.out.println("member is working..." + msg);
	}

}
