package com.jdc.mapping.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.mapping.model.dto.Level;

@Controller
@RequestMapping("inputs")
public class InputController {
	
	/*
	   This can be solved like this in ServletConfig
	
 		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addRedirectViewController("inputs", "inputs");
		}
	 */
	
	
	@GetMapping
	public String index() {
		return "inputs";
	}

	/*
		path -> localhost:8080/Something/search/some digits
		Something -> type, some digit -> id 
	 */
	@GetMapping("{type}/search/{id:\\d+}")
	public String findByType(
			@PathVariable String type,  // if {type} and this type don't match need @PathVariable(name="type")
			@PathVariable Integer id,  // in case id is null use Integer, int can't cast to null
			Model model) {
		model.addAttribute("type", type);
		model.addAttribute("id", id);
		
		return "inputs";
	}
	
	@GetMapping("{date:\\d{4}\\-\\d{2}\\-\\d{2}}")
	public String date(
			@PathVariable
			@DateTimeFormat(pattern = "yyyy-MM-dd")
			LocalDate date,  
			Model model) {
		model.addAttribute("date", date);
		return "inputs";
	}
	
	@GetMapping("{level:Basic|Intermediate|Advanced}")
	public String enumType(
			@PathVariable
			Level level,
			Model model) {
		model.addAttribute("level", level);
		return "inputs";
	}
	
	/* It needs RequestMappingHandlerMapping in Configuration file.
	 * When ; comes in url path, it has been removed automatically by RequestMappingHandlerMapping.
	 * So we need to mapping.setRemoveSemicolonContent(false);
	 * and need to override RequestMappingHandlerMapping of Spring by using @Bean(name = "requestMappingHandlerMapping")
	 * 
	 * 
	 * SHOULD NOT USE
	 * Path -> http://localhost:8080/inputs/matrix/Jersey;s=L;
	 * */
	
	@GetMapping("/matrix/{product}")
	public String useMatrix(
			@PathVariable String product,
			@MatrixVariable(name = "s") String size,
			@MatrixVariable(value = "c", required=false, defaultValue = "1") int count,  // if no count set 1 auto
			Model model) {
		
		var message = "Product: %s, size: %s, count: %s".formatted(product, size, count);
		model.addAttribute("product", message);
		return "inputs";
	}
	
	@GetMapping("/request")  // just need path
	public String useRequestParams(
			@RequestParam("p") String product,
			@RequestParam("l") int length,
			@RequestParam("w") int width,
			Model model) {
		
		var message = "Product: %s, length: %s, width: %s".formatted(product, length, width);
		model.addAttribute("product", message);
		return "inputs";
	}

}
