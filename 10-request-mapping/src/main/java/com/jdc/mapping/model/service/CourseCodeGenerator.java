package com.jdc.mapping.model.service;

import org.springframework.stereotype.Component;

import com.jdc.mapping.model.dto.Course;

@Component
public class CourseCodeGenerator {
	Course course = new Course();
	int id = course.getId();
	
	public int next() {
		return ++id;
	}
}
