package com.jdc.demo.dto;

import java.time.LocalDate;

public record Registration(
		int id,
		int classId, 
		Student student,
		LocalDate resgistrationDate
		) {

}
