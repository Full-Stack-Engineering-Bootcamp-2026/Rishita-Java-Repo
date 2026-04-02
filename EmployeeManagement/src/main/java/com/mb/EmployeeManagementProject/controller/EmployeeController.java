package com.mb.EmployeeManagementProject.controller;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mb.EmployeeManagementProject.dto.ApiResponse;
import com.mb.EmployeeManagementProject.model.Employee;
import com.mb.EmployeeManagementProject.service.EmployeeService;

@Validated
@RestController
@RequestMapping(value = "/employees", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class EmployeeController {

	private final EmployeeService service;

	public EmployeeController(EmployeeService service) {
		this.service = service;
	}
	//get all

	@GetMapping
	public ResponseEntity<ApiResponse<List<Employee>>> getAll() {
		return ResponseEntity.ok(new ApiResponse<>("all employees", service.findAll()));
	}
	
	//get by id

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Employee>> getById(@PathVariable @Min(value = 1, message = "ID must be +ve") int id) {

		return ResponseEntity.ok(new ApiResponse<>("Employee found", service.findById(id)));
	}

//post
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ApiResponse<Employee>> create(@Valid @RequestBody Employee emp,@RequestParam @Min(value = 1, message = "departmentId must be +ve") int departmentId) {
		Employee saved = service.create(emp, departmentId);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Employee created successfully", saved));
	}

	//update

	@PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ApiResponse<Employee>> update(@PathVariable @Min(value = 1, message = "ID must be +ve") int id, @Valid @RequestBody Employee emp,@RequestParam @Min(value = 1, message = "departmentId must be +ve") int departmentId) {

		return ResponseEntity.ok(new ApiResponse<>("Employee updated successfully", service.update(id, emp, departmentId)));
	}
	//delete

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable @Min(value = 1, message = "ID must be +ve") int id) {

		service.delete(id);
		return ResponseEntity.noContent().build(); 
	}
	

	@GetMapping("/count")
	public ResponseEntity<ApiResponse<Long>> count() {
		return ResponseEntity.ok(new ApiResponse<>("Total employee count", service.count()));
	}
}