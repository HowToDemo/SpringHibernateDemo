package com.portfolio.hibernateapp.exception;

import lombok.Getter;
import lombok.Setter;

public class ApiException extends RuntimeException {

	@Getter @Setter private String message;
	
	public ApiException(String message) {
		this.message = message;
	}
}
