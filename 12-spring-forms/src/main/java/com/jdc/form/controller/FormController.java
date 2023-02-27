package com.jdc.form.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.form.model.DataHolder;
import com.jdc.form.model.UserInput;
import com.jdc.form.model.UserInput.Gender;

@Controller
@RequestMapping("form")
public class FormController {

	@Autowired
	DataHolder repo;
	
	@GetMapping
	void index() {
	}
	
	@PostMapping 
	String redirect(@ModelAttribute("userInput") UserInput data) {
		repo.add(data);
		return "redirect:/form";
	}

	@ModelAttribute("userInput")
	UserInput userInput() {
		return new UserInput();
	}
	
	@ModelAttribute("list")
	List<UserInput> list() {
		return repo.getAll();
	}
	
	@ModelAttribute("courses") 
	List<String> courses() {
		return List.of("Java Basic", "React", "Angular", "Aws");
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
