package com.mb.employeemanagement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MindbowserEmailValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface MindbowserEmail {

	String message() default "Email must be a valid Mindbowser address";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
