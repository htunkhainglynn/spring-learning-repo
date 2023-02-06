package com.jdc.demo.admin.service;

import org.springframework.stereotype.Service;

import com.jdc.demo.HelloEnable;
import com.jdc.demo.LoggerBean;

@Service
@LoggerBean
public class AdminService implements HelloEnable {

	@Override
	public void doWork(String msg) {
		System.out.println("admin is working..." + msg);
	}
	
	public void doJob(int a, int b) {
		System.out.println("no job");
	}

}
