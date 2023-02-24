package com.jdc.mapping.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.jdc.mapping.model.dto.Course;

@Service
public class CourseService {
	
	@Autowired
	private SimpleJdbcInsert jdbc;
	
	BeanPropertyRowMapper<Course> rowMapper;
	
	public CourseService() {
		rowMapper = new BeanPropertyRowMapper<Course>(Course.class);
	}
	
	
	@Value(value = "${course.select}")
	String selectSql;
	
	@Value(value = "${course.select.all}")
	String selectAllSql;
	
	@Value(value = "${course.update}")
	String updateSql;
	/*
	This does not need to give specific sql string. Just use jdbc.executeAndReturnKey(params).intValue() 
	and it returns the id.
	*/
	public int save(Course c) { 
		
		if(c.getId() > 0) {
			jdbc.getJdbcTemplate().update(updateSql, 
					c.getName(), c.getLevel().name(), c.getDuration(), c.getFees(), c.getId());
			return c.getId();
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", c.getName());
		params.put("level", c.getLevel().name());
		params.put("duration", c.getDuration());
		params.put("fees", c.getFees());
		return jdbc.executeAndReturnKey(params).intValue();
	}

	// queryForObject can't handle if there is null so use stream
	public Course findById(int id) {
		Course course = jdbc.getJdbcTemplate().query(selectSql, rowMapper, id).stream().findAny().orElse(null);
		return course;
	}

	public List<Course> getAll() {
		List<Course> list = jdbc.getJdbcTemplate().query(selectAllSql, rowMapper);
		return list;
	}
}
