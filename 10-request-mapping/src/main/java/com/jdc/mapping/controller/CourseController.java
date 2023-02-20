package com.jdc.mapping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.mapping.model.service.CourseService;


@Controller
@RequestMapping("course")
public class CourseController {
	
	@Autowired
	private CourseService service;

//	@GetMapping  // do not work in xml style
//	public String index(Model model) {
//		model.addAttribute("list", service.getAll());
//		return "course";
//	}
	
	@GetMapping
	public String index(ModelMap model) {
		model.put("list", service.getAll());
		return "course";
	}
}
