package com.jdc.form.mvc.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jdc.form.root.dto.CourseDto;
import com.jdc.form.root.services.CourseDao;

@Component
public class CourseConverter implements Converter<String, CourseDto> {

	@Autowired
	private CourseDao repo;
	
	@Override
	public CourseDto convert(String value) {
		if (StringUtils.hasLength(value)) {
			int id = Integer.parseInt(value);
			return repo.findById(id);
		}
		return null;
	}

}
