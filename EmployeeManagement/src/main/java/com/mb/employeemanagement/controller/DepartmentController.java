package com.mb.employeemanagement.controller;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mb.employeemanagement.dto.request.DepartmentRequest;
import com.mb.employeemanagement.dto.response.DepartmentResponse;
import com.mb.employeemanagement.dto.response.ApiResponse;
import com.mb.employeemanagement.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/departments", produces = { MediaType.APPLICATION_JSON_VALUE,
		MediaType.APPLICATION_XML_VALUE })
public class DepartmentController {

	private final DepartmentService service;


	
	//get all
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<DepartmentResponse>>> getAll() {
		return ResponseEntity.ok(new ApiResponse<>("All departments", service.findAll()));
	}
	
	//get byt id

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<DepartmentResponse>> getById(@PathVariable @Min(value = 1, message = "ID must be greater than 0") int id) {

		return ResponseEntity.ok(new ApiResponse<>("Department:", service.findById(id)));
	}
	
	//create

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ApiResponse<DepartmentResponse>> create(@Valid @RequestBody DepartmentRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Department created", service.create(request)));
	}

	//update
	@PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ApiResponse<DepartmentResponse>> update(@PathVariable @Min(value = 1, message = "ID must be greater than 0") int id, @Valid @RequestBody DepartmentRequest request) {

		return ResponseEntity.ok(new ApiResponse<>("Department updated", service.update(id, request)));
	}

	//delete
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Object>> delete(@PathVariable @Min(value = 1, message = "ID must be greater than 0") int id) {
		service.delete(id);
		return ResponseEntity.ok(new ApiResponse<>("Department deleted successfully", null));
	}
}
