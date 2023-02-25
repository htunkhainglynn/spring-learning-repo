package com.jdc.scope.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.scope.model.Counter;

@Controller
public class HomeController {

	@Autowired  // set scope type in RootConfig file
	private Counter requestCounter;
	
	@Autowired
	private Counter sessionCounter;
	
	@Autowired
	private Counter applicationCounter;
	
	@GetMapping
	public String index(ModelMap model) {
		model.addAttribute("request", requestCounter.countUp());
		model.addAttribute("session", sessionCounter.countUp());
		model.addAttribute("application", applicationCounter.countUp());
		return "home";
	}
}
