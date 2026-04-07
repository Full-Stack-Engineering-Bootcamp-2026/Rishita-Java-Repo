package com.mb.employeemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.validation.ConstraintViolationException;
import com.mb.employeemanagement.dto.response.ApiResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {

	// 1. Request Body exceptions-@Valid 
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<String>> handleValidation(MethodArgumentNotValidException ex) {
		String error = ex.getBindingResult().getFieldError() != null ? 
			ex.getBindingResult().getFieldError().getDefaultMessage() : "Validation failed";
		return ResponseEntity.badRequest().body(new ApiResponse<>("ERR-VALIDATION", error, null));
	}

	// 2. PathVariable exceptions-string instead of int
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiResponse<String>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		String error = "Invalid value for parameter: " + ex.getName();
		return ResponseEntity.badRequest().body(new ApiResponse<>("ERR-TYPE-MISMATCH", error, null));
	}

	// 2. PathVariable constraint violations @Min(1)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiResponse<String>> handleConstraintViolation(ConstraintViolationException ex) {
		return ResponseEntity.badRequest().body(new ApiResponse<>("ERR-CONSTRAINT", ex.getMessage(), null));
	}

	// 3. Custom exceptions InvalidLeaveException
	@ExceptionHandler(InvalidLeaveException.class)
	public ResponseEntity<ApiResponse<String>> handleCustomException(InvalidLeaveException ex) {
		return ResponseEntity.badRequest().body(new ApiResponse<>("ERR-INVALID-LEAVE", ex.getMessage(), null));
	}

	// 4. Null Pointer exceptions
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ApiResponse<String>> handleNullPointer(NullPointerException ex) {
		// Hide the actual NPE trace and safely return an error indicator
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiResponse<>("ERR-NULL-POINTER", "A null reference was encountered internally", null));
	}

	// Catch-all generic exception handler
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<String>> handleGeneric(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiResponse<>("ERR-INTERNAL", "Something went wrong: " + ex.getMessage(), null));
	}

	// General Runtime Exception handler
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiResponse<String>> handleRuntime(RuntimeException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ApiResponse<>("ERR-RUNTIME", ex.getMessage(), null));
	}
}
