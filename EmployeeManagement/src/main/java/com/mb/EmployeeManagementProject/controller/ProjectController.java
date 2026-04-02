package com.mb.EmployeeManagementProject.controller;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mb.EmployeeManagementProject.dto.ApiResponse;
import com.mb.EmployeeManagementProject.model.Project;
import com.mb.EmployeeManagementProject.service.ProjectService;

@Validated
@RestController
@RequestMapping(value = "/projects", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class ProjectController {

	private final ProjectService service;

	public ProjectController(ProjectService service) {
		this.service = service;
	}

	//getall
	@GetMapping
	public ResponseEntity<ApiResponse<List<Project>>> getAll() {
		return ResponseEntity.ok(new ApiResponse<>("All projects", service.findAll()));
	}
	//get by id

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Project>> getById(@PathVariable @Min(value = 1, message = "ID must be +ve") int id) {
		return ResponseEntity.ok(new ApiResponse<>("Project found", service.findById(id)));
	}

//by empid
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<ApiResponse<List<Project>>> getByEmployee(@PathVariable @Min(value = 1, message = "employeeId must be +ve") int employeeId) {
		return ResponseEntity.ok(new ApiResponse<>("Projects for employee " + employeeId, service.findByEmployeeId(employeeId)));
	}

	//create
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ApiResponse<Project>> create(@Valid @RequestBody Project project) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Project created", service.create(project)));
	}

	//update
	@PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ApiResponse<Project>> update(@PathVariable @Min(value = 1, message = "ID must be +ve") int id, @Valid @RequestBody Project project) {
		return ResponseEntity.ok(new ApiResponse<>("Project updated", service.update(id, project)));
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable @Min(value = 1, message = "ID must be+ve") int id) {
		service.delete(id);
		return ResponseEntity.noContent().build(); 
	}
}