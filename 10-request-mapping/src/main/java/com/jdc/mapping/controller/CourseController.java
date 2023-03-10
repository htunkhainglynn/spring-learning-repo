package com.jdc.mapping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String edit() {
		return "course-edit";
	}
	
	@PostMapping // if post comes in, redirect to course
	public String save(
			@ModelAttribute Course course,  // this can create course object automatically(cool)
			RedirectAttributes redirect) {
		var id = service.save(course);
		
		if (id == course.getId()) {
			redirect.addFlashAttribute("result", new Result(status.Success, "Successfully updated!"));
		}
		else {
			redirect.addFlashAttribute("result", new Result(status.Success, "Successfully created!"));  // test with object
		}
		// redirect.addAttribute("result", new Result(status.Success, "Successfully created!"));  // will prompt error, can't change object to String
		return "redirect:/course/detail?id=%d".formatted(id); // redirect a url
	}
	
	// about flashAttribute read in README
	
	/*
	 Suppose user makes a double submit and there is no redirect, if user refresh the page the request will send with post method again.
	 So if we use redirect, the first time user submits, the in this page I create a course and id then i redirect it to another route implicitly.
	 So if user makes another request, it has been redirected to this getMapping route and automatically prevents double submits problem.
	*/
	@GetMapping("detail") // catch url the way the url is directed
	public String findById(@RequestParam int id, ModelMap model) {
		var course = service.findById(id);
		model.put("course", course);
		return "course-details";
	}
	
	@ModelAttribute
	public void loadLevels(ModelMap model) {
		model.put("levels", Level.values());
	}

//	@ModelAttribute("levels")
//	public Level[] loadLevels(ModelMap model) {
//		return Level.values();
//	}
	
	@ModelAttribute
	public void loadCourse(@RequestParam(required = false) Integer id, ModelMap model) {
		if (id != null) {  // if id is not null, load the model if null go default route
			model.put("course", service.findById(id));
		}
	}
}
