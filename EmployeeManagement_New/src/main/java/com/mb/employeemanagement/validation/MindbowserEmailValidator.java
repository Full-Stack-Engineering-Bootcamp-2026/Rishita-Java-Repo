package com.mb.employeemanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class MindbowserEmailValidator implements ConstraintValidator<MindbowserEmail, String> {

	private static final Pattern MINDBOWSER_EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@mindbowser\\.com$");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isBlank()) {
			return false; 
		}
		return MINDBOWSER_EMAIL_PATTERN.matcher(value).matches();
	}
}
