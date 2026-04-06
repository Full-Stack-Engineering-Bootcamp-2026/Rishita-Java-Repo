package com.mb.employeemanagement.controller;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mb.employeemanagement.dto.request.EmployeeRequest;
import com.mb.employeemanagement.dto.response.EmployeeResponse;
import com.mb.employeemanagement.dto.response.ApiResponse;
import com.mb.employeemanagement.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/employees", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class EmployeeController {

	private final EmployeeService service;


	//get all

	@GetMapping
	public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getAll() {
		return ResponseEntity.ok(new ApiResponse<>("all employees", service.findAll()));
	}
	
	//get by id

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<EmployeeResponse>> getById(@PathVariable @Min(value = 1, message = "ID must be greater than 0") int id) {

		return ResponseEntity.ok(new ApiResponse<>("Employee found", service.findById(id)));
	}
	
	@GetMapping("/department/{name}")
	public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getByDept(@PathVariable String name) {
	    return ResponseEntity.ok(
	        new ApiResponse<>("Employees by department", service.findByDepartmentName(name))
	    );
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ApiResponse<EmployeeResponse>> create(@Valid @RequestBody EmployeeRequest request,@RequestParam @Min(value = 1, message = "Department ID must be greater than 0") int departmentId) {
		EmployeeResponse saved = service.create(request, departmentId);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Employee created successfully", saved));
	}

	//update

	@PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ApiResponse<EmployeeResponse>> update(@PathVariable @Min(value = 1, message = "ID must be greater than 0") int id, @Valid @RequestBody EmployeeRequest request,@RequestParam @Min(value = 1, message = "Department ID must be greater than 0") int departmentId) {

		return ResponseEntity.ok(new ApiResponse<>("Employee updated successfully", service.update(id, request, departmentId)));
	}
	//delete

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Object>> delete(@PathVariable @Min(value = 1, message = "ID must be greater than 0") int id) {

		service.delete(id);
		return ResponseEntity.ok(new ApiResponse<>("Employee deleted successfully", null)); 
	}
	

	@GetMapping("/count")
	public ResponseEntity<ApiResponse<Long>> count() {
		return ResponseEntity.ok(new ApiResponse<>("Total employee count", service.count()));
	}
}
