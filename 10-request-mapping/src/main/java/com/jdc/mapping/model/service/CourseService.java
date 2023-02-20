package com.jdc.mapping.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.mapping.model.dto.Course;

@Service
public class CourseService {

	@Autowired
	CourseCodeGenerator generator;
	
	ArrayList<Course> repo;
	
	public CourseService() {
		repo = new ArrayList<Course>();
	}
	
	// it is post construct, so you can call create
	// it is used to add default data when application is started.(application context)
	@PostConstruct  
	public void init() {
		create(new Course("Java Basic", "Basic", 4, 200000));
		create(new Course("JavaScript Basic", "Basic", 3, 250000));
		create(new Course("Java Spring", "Advanced", 3, 300000));
		create(new Course("React", "Advanced", 5, 280000));
	}
	
	public int create(Course c) {
		var id = generator.next();
		c.setId(id);
		repo.add(c);
		return id;
	}
	
	public Course findById(int id) {
		return repo.stream().filter(c -> c.getId() == id )
				.findAny().orElse(null);
	}
	
	public List<Course> getAll() {
		return List.copyOf(repo);
	}
}
