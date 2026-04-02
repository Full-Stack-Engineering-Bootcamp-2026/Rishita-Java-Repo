package com.mb.EmployeeManagementProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidLeaveException extends RuntimeException {

	public InvalidLeaveException(String message) {
		super(message);
	}
}