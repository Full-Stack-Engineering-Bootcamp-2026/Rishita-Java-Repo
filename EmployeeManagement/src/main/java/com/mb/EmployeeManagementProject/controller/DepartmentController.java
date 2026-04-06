package com.mb.EmployeeManagementProject.controller;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mb.EmployeeManagementProject.dto.ApiResponse;
import com.mb.EmployeeManagementProject.model.Department;
import com.mb.EmployeeManagementProject.service.DepartmentService;

@Validated
@RestController
@RequestMapping(value = "/departments", produces = { MediaType.APPLICATION_JSON_VALUE,
		MediaType.APPLICATION_XML_VALUE })
public class DepartmentController {

	private final DepartmentService service;

	public DepartmentController(DepartmentService service) {
		this.service = service;
	}
	
	//get all
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<Department>>> getAll() {
		return ResponseEntity.ok(new ApiResponse<>("All departments", service.findAll()));
	}
	
	//get byt id

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Department>> getById(@PathVariable @Min(value = 1, message = "ID must be +ve") int id) {

		return ResponseEntity.ok(new ApiResponse<>("Department:", service.findById(id)));
	}
	
	//create

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ApiResponse<Department>> create(@Valid @RequestBody Department dept) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Department created", service.create(dept)));
	}

	//update
	@PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ApiResponse<Department>> update(@PathVariable @Min(value = 1, message = "ID must be+ve") int id, @Valid @RequestBody Department dept) {

		return ResponseEntity.ok(new ApiResponse<>("Department updated", service.update(id, dept)));
	}

	//delete
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable @Min(value = 1, message = "ID must be+ve") int id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}