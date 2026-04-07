package com.mb.employeemanagement.dto.response;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "apiResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<T> {

	private String errorCode;
	private String message;
	private T data;

	public ApiResponse(String message, T data) {
		this.message = message;
		this.data = data;
	}

	public ApiResponse(String errorCode, String message, T data) {
		this.errorCode = errorCode;
		this.message = message;
		this.data = data;
	}
}
