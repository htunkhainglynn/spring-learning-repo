package com.jdc.form.converter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jdc.form.model.dao.CourseDao;
import com.jdc.form.model.dto.CourseDto;

@Component
public class CourseFormatter implements Formatter<CourseDto> {

	@Autowired
	CourseDao repo;
	
	@Override
	public String print(CourseDto object, Locale locale) {
		if (null != object) {
			return object.getName();
		}
		return null;
	}

	@Override
	public CourseDto parse(String text, Locale locale) throws ParseException {
		if (StringUtils.hasLength(text)) {
			int id = Integer.parseInt(text); // option value from form.jsp is id
			return repo.findById(id);
		}
		return null;
	}

}
