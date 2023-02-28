package com.jdc.form.model.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

public class UserInput {

	private int id;
	private String name;
	private String phone;
	private String email;
	private String password;
	private CourseDto course;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate registration;
	private Gender gender;
	private List<String> knowledge;
	private boolean agree;
	private String remark;
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public enum Gender {
		Male, Female, Other
	}
	
	public LocalDate getRegistration() {
		return registration;
	}

	public void setRegistration(LocalDate registration) {
		this.registration = registration;
	}
	
//	public UserInput(int id, String name, String phone, String email, String password, String course,
//			String registration, Gender gender, List<String> knowledge, boolean agree) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.phone = phone;
//		this.email = email;
//		this.password = password;
//		this.course = course;
//		this.registration = registration;
//		this.gender = gender;
//		this.knowledge = knowledge;
//		this.agree = agree;
//	}

	public List<String> getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(List<String> knowledge) {
		this.knowledge = knowledge;
	}

	public boolean isAgree() {
		return agree;
	}

	public void setAgree(boolean agree) {
		this.agree = agree;
	}

	@Override
	public int hashCode() {
		return Objects.hash(course, email, id, name, password, phone);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInput other = (UserInput) obj;
		return Objects.equals(course, other.course) && Objects.equals(email, other.email) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(phone, other.phone);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public CourseDto getCourse() {
		return course;
	}
	public void setCourse(CourseDto course) {
		this.course = course;
	}
}
