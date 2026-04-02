package com.mb.EmployeeManagementProject.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MindbowserEmailValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface MindbowserEmail {

	String message() default "Email must be@mindbowser";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}