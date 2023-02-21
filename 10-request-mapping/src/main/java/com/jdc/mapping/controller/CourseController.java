package com.jdc.mapping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdc.mapping.model.dto.Course;
import com.jdc.mapping.model.dto.Level;
import com.jdc.mapping.model.dto.Result;
import com.jdc.mapping.model.dto.Result.status;
import com.jdc.mapping.model.service.CourseService;


@Controller
@RequestMapping("course")
public class CourseController {
	
	@Autowired
	private CourseService service;

	@GetMapping  
	public String index(Model model) {
		model.addAttribute("list", service.getAll());
		return "course";
	}
	
//	@GetMapping
//	public String index(ModelMap model) {
//		model.put("list", service.getAll());
//		return "course";
//	}
	
	@GetMapping("edit")
	public String edit(Model model) {
		return "course-edit";
	}
	
	@PostMapping // if post comes in, redirect to course
	public String save(
			@RequestParam String name,
			@RequestParam Level level,
			@RequestParam int duration,
			@RequestParam int fees,
			RedirectAttributes redirect) {
		var course = new Course(name, level, duration, fees);
		var id = service.create(course);
		redirect.addFlashAttribute("result", new Result(status.Success, "Successfully created!"));  // test with object
		// redirect.addAttribute("result", new Result(status.Success, "Successfully created!"));  // will prompt error, can't change object to String
		return "redirect:/course/detail?id=%d".formatted(id); // redirect a url
	}
	
	// about flashAttribute read in README
	
	// create comes in post route and find and show comes in get route so it can prevent double submits
	
	@GetMapping("detail") // catch url the way the url is directed
	public String findById(@RequestParam int id, ModelMap model) {
		var course = service.findById(id);
		model.put("course", course);
		return "course-details";
	}
}
