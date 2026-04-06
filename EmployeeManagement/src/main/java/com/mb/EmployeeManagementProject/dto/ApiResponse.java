package com.mb.EmployeeManagementProject.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "apiResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApiResponse<T> {

	private String message;
	private T data;

	public ApiResponse() {
	}

	public ApiResponse(String message, T data) {
		this.message = message;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}
}