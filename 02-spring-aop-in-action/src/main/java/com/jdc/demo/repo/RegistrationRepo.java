package com.jdc.demo.repo;

import java.time.LocalDate;

import org.springframework.stereotype.Repository;

import com.jdc.demo.dto.Classes;
import com.jdc.demo.dto.Registration;
import com.jdc.demo.dto.Student;

@Repository
public class RegistrationRepo {

	public Registration register(Classes classes, Student newStudent, LocalDate date) {
		System.out.println("registered successfully");
		return null;
	}

}
