package com.jdc.demo.dto;

import java.time.LocalDate;

public record Classes(
		int id,
		Courses course, 
		LocalDate startDate,
		int months
		) {

}
