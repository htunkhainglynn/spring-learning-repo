package com.jdc.mapping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("multi")
public class MultiController {

	@RequestMapping
	public String index() {
		return "multi";
	}
	
	/*
	 * url -> multi/action1
	 * */
	@GetMapping(value = "action1", params = "id")
	public String action1(Model model) {
		model.addAttribute("message", "Action 1");
		return "multi";
	}
	
	/*
	 * url -> multi/action2
	 * */
	@RequestMapping("action2")
	public String action1(ModelMap model) {
		model.put("message", "Action 2");
		return "multi";
	}
	
	/*
	 * url -> multi/INVALID THINGS
	 * */
	@RequestMapping("**")  
	public String other(Model model) {
		model.addAttribute("message", "Other action");
		return "multi";
	}
	
	@RequestMapping("{:\\d+}")  
	public String numbers(Model model) {
		model.addAttribute("message", "Numbers");
		return "multi";
	}
}
