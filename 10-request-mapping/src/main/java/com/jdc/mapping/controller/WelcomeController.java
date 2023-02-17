package com.jdc.mapping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class WelcomeController{
	
	@RequestMapping({"/", "hello", "home"})
	public String index() {
		return "home";
	}
}
