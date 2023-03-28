package com.jdc.book.mvc.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdc.book.root.dto.Book;
import com.jdc.book.root.dto.Category;
import com.jdc.book.root.formatter.CategoryFormatter;
import com.jdc.book.root.service.BookService;
import com.jdc.book.root.service.CategoryService;

@Controller
@RequestMapping({"/home", "/book"})
public class HomeController {
	
	@Autowired
	CategoryFormatter categoryFormatter;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BookService bookService;
	
	@InitBinder
	void initBinder(WebDataBinder binder) {
		binder.addCustomFormatter(categoryFormatter);
	}

	@GetMapping
	public String home(
			@RequestParam(required = false) Integer category,
			@RequestParam(required = false) String keyword,
			Model model
			) {
		
		var list = bookService.search(category, keyword);
		model.addAttribute("list", list);
		return "home";
	}
	
	@GetMapping("edit")
	public String edit(@ModelAttribute("book") Book book) {
		bookService.save(book);
		return "edit-book";
	}
	
	@GetMapping("details")
	public String details(@RequestParam("id") int id, Model model) {
	    return "details";
	}

	@PostMapping()
	public String save(@Validated @ModelAttribute("book") Book book, 
						BindingResult result, 
						RedirectAttributes redirectAttributes) {
	    if (result.hasErrors()) {
	        return "edit-book";
	    }
	    int id = bookService.save(book);
	    redirectAttributes.addAttribute("id", id);
	    return "redirect:/book/details";
	}

	
	@ModelAttribute("categories")
	public List<Category> categories() {
		return categoryService.getAllCategories();
	}
	
	@ModelAttribute("book")
	public Book book(@RequestParam(required = false) Optional<Integer> id) {
	    return id.flatMap(bookService::findById).orElseGet(Book::new);
	}
	
	@ModelAttribute("list") 
	public List<Book> bookList(){
		return bookService.getAllBook();
	}

}
