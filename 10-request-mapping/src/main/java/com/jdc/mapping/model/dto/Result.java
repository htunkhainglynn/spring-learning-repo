package com.jdc.mapping.model.dto;

import java.util.Objects;

public class Result {

	private status status;
	private String message;

	@Override
	public int hashCode() {
		return Objects.hash(message, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Result other = (Result) obj;
		return Objects.equals(message, other.message) && Objects.equals(status, other.status);
	}

	public status getStatus() {
		return status;
	}

	public Result(status success, String message) {
		this.status = success;
		this.message = message;
	}

	public Result() {
	}

	public void setStatus(status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public enum status {
		Success, Warning, Error
	}
}
