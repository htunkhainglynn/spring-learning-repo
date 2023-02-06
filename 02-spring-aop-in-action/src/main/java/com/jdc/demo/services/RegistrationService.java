package com.jdc.demo.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.demo.dto.Classes;
import com.jdc.demo.dto.Registration;
import com.jdc.demo.dto.Student;
import com.jdc.demo.repo.RegistrationRepo;
import com.jdc.demo.repo.StudentRepo;

@Service
public class RegistrationService {
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private RegistrationRepo registrationRepo;


	public Registration register(Classes classes, Student student, LocalDate date) {
		
		// add register student to student db
		Student newStudent = studentRepo.register(student);
		
		// add registration to registration db
		Registration registration = registrationRepo.register(classes, newStudent, date);
		
		
		return registration;		
		
	}
}
