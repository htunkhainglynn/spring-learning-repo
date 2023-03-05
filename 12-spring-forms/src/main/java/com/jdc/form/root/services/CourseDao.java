package com.jdc.form.root.services;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;

import com.jdc.form.root.dto.CourseDto;

@Repository
@ApplicationScope
public class CourseDao {

	private List<CourseDto> courses = List.of(
			new CourseDto(1, "Java", 20000),
			new CourseDto(2, "Angular", 250000),
			new CourseDto(3, "Spring", 300000),
			new CourseDto(4, "React", 200000)
			);
	
	public List<CourseDto> getCourse() {
		return courses;
	}

	public CourseDto findById(int id) {
		return courses.stream().filter(course -> course.getId() == id).findAny().orElse(null);
	}
}
