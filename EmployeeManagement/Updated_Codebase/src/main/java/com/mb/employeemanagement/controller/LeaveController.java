package com.mb.employeemanagement.controller;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mb.employeemanagement.dto.request.LeaveRequest;
import com.mb.employeemanagement.dto.response.LeaveResponse;
import com.mb.employeemanagement.enums.LeaveStatus;
import com.mb.employeemanagement.dto.response.ApiResponse;
import com.mb.employeemanagement.service.LeaveService;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/leaves", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class LeaveController {

	private final LeaveService service;



	//get all
	@GetMapping
	public ResponseEntity<ApiResponse<List<LeaveResponse>>> getAll() {
		return ResponseEntity.ok(new ApiResponse<>("All records", service.findAll()));
	}

	//get by id
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<LeaveResponse>> getById(@PathVariable @Min(value = 1, message = "ID must be greater than 0") int id) {

		return ResponseEntity.ok(new ApiResponse<>("Leave found", service.findById(id)));
	}

	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<ApiResponse<List<LeaveResponse>>> getByEmployee(@PathVariable @Min(value = 1, message = "Employee ID must be greater than 0") int employeeId) {

		return ResponseEntity.ok(new ApiResponse<>("Leaves for employee " + employeeId, service.findByEmployeeId(employeeId)));
	}

	//create

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ApiResponse<LeaveResponse>> apply(@Valid @RequestBody LeaveRequest request,@RequestParam @Min(value = 1, message = "Employee ID must be greater than 0") int employeeId) {

		return ResponseEntity.status(HttpStatus.CREATED) .body(new ApiResponse<>("Leave applied successfully", service.apply(request, employeeId)));
	}

	//delete

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Object>> delete(@PathVariable @Min(value = 1, message = "ID must be greater than 0") int id) {
		service.delete(id);
		return ResponseEntity.ok(new ApiResponse<>("Leave deleted successfully", null)); 
	}
	@PutMapping("/{id}/status")
	public ResponseEntity<ApiResponse<Object>> updateStatus(
	        @PathVariable int id,
	        @RequestParam LeaveStatus status) {

	    return ResponseEntity.ok(
	        new ApiResponse<>("Status updated", service.updateStatus(id, status))
	    );
	}
}
