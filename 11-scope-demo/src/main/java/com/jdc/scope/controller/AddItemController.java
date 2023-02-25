package com.jdc.scope.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.jdc.scope.model.StringCart;

@Controller
public class AddItemController {

	@PostMapping("add-item")
	String add(@RequestParam String item, @SessionAttribute StringCart cart) {
		cart.add(item);
		return "cart-view";
	}
}
