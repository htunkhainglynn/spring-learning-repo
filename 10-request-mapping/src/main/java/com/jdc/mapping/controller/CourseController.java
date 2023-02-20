package com.jdc.mapping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jdc.mapping.model.service.CourseService;


@Controller
@RequestMapping("course")
public class CourseController {
	
	@Autowired
	private CourseService service;

	@GetMapping
	public ModelAndView index() {
		var modelView = new ModelAndView();
		modelView.getModel().put("list", service.getAll());
		modelView.setViewName("course");
		return modelView;
	}
}
