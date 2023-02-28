package com.jdc.form.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.form.converter.CourseConverter;
import com.jdc.form.converter.CourseFormatter;
import com.jdc.form.model.dao.CourseDao;
import com.jdc.form.model.dao.DataHolder;
import com.jdc.form.model.dto.CourseDto;
import com.jdc.form.model.dto.UserInput;
import com.jdc.form.model.dto.UserInput.Gender;

@Controller
@RequestMapping("form")
public class FormController {

	@Autowired
	DataHolder repo;
	
	@Autowired
	CourseDao courseRepo;
	
//	@Autowired 
//	CourseConverter courseConverter;
	
	@Autowired
	CourseFormatter courseFormatter;
	
	@GetMapping
	String index() {
		return "form";
	}
	
//	using converter
//	@InitBinder
//	void intiBinder(WebDataBinder binder) {
//		if (binder.getConversionService() instanceof ConfigurableConversionService registry) {
//			registry.addConverter(courseConverter);
//		}
//	}
	
	@InitBinder
	void intiBinder(WebDataBinder binder) {
		binder.addCustomFormatter(courseFormatter);
	}
	
	@PostMapping 
	String redirect(@ModelAttribute("userInput") UserInput data) {
		repo.add(data);
		return "redirect:/form";
	}
	
	@ModelAttribute("courses")
	List<CourseDto> course() {
		return courseRepo.getCourse();
	}

	@ModelAttribute("userInput")
	UserInput userInput() {
		return new UserInput();
	}
	
	@ModelAttribute("list")
	List<UserInput> list() {
		return repo.getAll();
	}

	
	@ModelAttribute("genders") 
	Gender[] genders() {
		return Gender.values();
	}
	
	@ModelAttribute("knowledges") 
	List<String> knowledges() {
		UserInput userInput = new UserInput();
		userInput.setKnowledge(List.of("html", "css", "javascript"));
		return userInput.getKnowledge();
	}
}
