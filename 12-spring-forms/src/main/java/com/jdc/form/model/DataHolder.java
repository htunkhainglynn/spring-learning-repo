package com.jdc.form.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
@ApplicationScope
public class DataHolder {
	
	private List<UserInput> repository;
	
	public DataHolder() {
		repository = new ArrayList<UserInput>();
	}
	
	public void add(UserInput userInput) {
		repository.add(userInput);
	}
	
	public List<UserInput> getAll() {
		return repository;
	}
	
}