package com.mb.employeemanagement.controller;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mb.employeemanagement.dto.request.ProjectRequest;
import com.mb.employeemanagement.dto.response.ProjectResponse;
import com.mb.employeemanagement.dto.response.ApiResponse;
import com.mb.employeemanagement.service.ProjectService;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/projects", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class ProjectController {

	private final ProjectService service;



	//getall
	@GetMapping
	public ResponseEntity<ApiResponse<List<ProjectResponse>>> getAll() {
		List<ProjectResponse> projectResponses = service.findAll();
		return ResponseEntity.ok(new ApiResponse<>("All projects", projectResponses));
	}
	//get by id

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ProjectResponse>> getById(@PathVariable @Min(value = 1, message = "ID must be greater than 0") int id) {
		return ResponseEntity.ok(new ApiResponse<>("Project found", service.findById(id)));
	}

//by empid
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<ApiResponse<List<ProjectResponse>>> getByEmployee(@PathVariable @Min(value = 1, message = "Employee ID must be greater than 0") int employeeId) {
		return ResponseEntity.ok(new ApiResponse<>("Projects for employee " + employeeId, service.findByEmployeeId(employeeId)));
	}

	//create
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ApiResponse<ProjectResponse>> create(@Valid @RequestBody ProjectRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Project created", service.create(request)));
	}

	//update
	@PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ApiResponse<ProjectResponse>> update(@PathVariable @Min(value = 1, message = "ID must be greater than 0") int id, @Valid @RequestBody ProjectRequest request) {
		return ResponseEntity.ok(new ApiResponse<>("Project updated", service.update(id, request)));
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Object>> delete(@PathVariable @Min(value = 1, message = "ID must be greater than 0") int id) {
		service.delete(id);
		return ResponseEntity.ok(new ApiResponse<>("Project deleted successfully", null)); 
	}
}
