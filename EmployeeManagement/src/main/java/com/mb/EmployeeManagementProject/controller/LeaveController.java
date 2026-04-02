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
import com.mb.EmployeeManagementProject.model.Leave;
import com.mb.EmployeeManagementProject.model.LeaveStatus;
import com.mb.EmployeeManagementProject.service.LeaveService;

@Validated
@RestController
@RequestMapping(value = "/leaves", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class LeaveController {

	private final LeaveService service;

	public LeaveController(LeaveService service) {
		this.service = service;
	}

	//get all
	@GetMapping
	public ResponseEntity<ApiResponse<List<Leave>>> getAll() {
		return ResponseEntity.ok(new ApiResponse<>("All records", service.findAll()));
	}

	//get by id
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Leave>> getById(@PathVariable @Min(value = 1, message = "ID must be+ve") int id) {

		return ResponseEntity.ok(new ApiResponse<>("Leave found", service.findById(id)));
	}

	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<ApiResponse<List<Leave>>> getByEmployee(@PathVariable @Min(value = 1, message = "employeeId must be+ve") int employeeId) {

		return ResponseEntity.ok(new ApiResponse<>("Leaves for employee " + employeeId, service.findByEmployeeId(employeeId)));
	}

	//create

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ApiResponse<Leave>> apply(@Valid @RequestBody Leave leave,@RequestParam @Min(value = 1, message = "employeeId must be +ve") int employeeId) {

		return ResponseEntity.status(HttpStatus.CREATED) .body(new ApiResponse<>("Leave applied successfully", service.apply(leave, employeeId)));
	}

	//delete

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable @Min(value = 1, message = "ID must be+ve") int id) {
		service.delete(id);
		return ResponseEntity.noContent().build(); 
	}
}