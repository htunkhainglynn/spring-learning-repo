package com.jdc.form.model.dto;

import java.util.Objects;

public class CourseDto {

	public CourseDto(int id, String name, int fees) {
		this.id = id;
		this.name = name;
		this.fees = fees;
	}
	private int id;
	private String name;
	private int fees;
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
	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
	@Override
	public int hashCode() {
		return Objects.hash(fees, id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseDto other = (CourseDto) obj;
		return Objects.equals(fees, other.fees) && id == other.id && Objects.equals(name, other.name);
	}
	
	
}
