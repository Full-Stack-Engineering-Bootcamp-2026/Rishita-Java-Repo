package com.mb.employeemanagement.controller;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mb.employeemanagement.dto.request.AddressRequest;
import com.mb.employeemanagement.dto.response.AddressResponse;
import com.mb.employeemanagement.dto.response.ApiResponse;
import com.mb.employeemanagement.service.AddressService;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/addresses", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class AddressController {

	private final AddressService service;


	
	//get all

	@GetMapping
	public ResponseEntity<ApiResponse<List<AddressResponse>>> getAll() {
		return ResponseEntity.ok(new ApiResponse<>("All addresses", service.findAll()));
	}
	
	 //get by id

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<AddressResponse>> getById(@PathVariable @Min(value = 1, message = "ID must be greater than 0") int id) {
		return ResponseEntity.ok(new ApiResponse<>("Address:", service.findById(id)));
	}

	//add by emp id
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<ApiResponse<AddressResponse>> getByEmployeeId(@PathVariable @Min(value = 1, message = "Employee ID must be greater than 0") int employeeId) {
		return ResponseEntity.ok(new ApiResponse<>("Address for employee " + employeeId, service.findByEmployeeId(employeeId)));
	}
	
	//create

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ApiResponse<AddressResponse>> create(@Valid @RequestBody AddressRequest request,@RequestParam @Min(value = 1, message = "Employee ID must be greater than 0") int employeeId) {

		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Address created", service.create(request, employeeId)));
	}

	//update
	@PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ApiResponse<AddressResponse>> update(@PathVariable @Min(value = 1, message = "ID must be greater than 0") int id, @Valid @RequestBody AddressRequest request) {

		return ResponseEntity.ok(new ApiResponse<>("Address updated", service.update(id, request)));
	}

	//delete
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Object>> delete(@PathVariable @Min(value = 1, message = "ID must be greater than 0") int id) {

		service.delete(id);
		return ResponseEntity.ok(new ApiResponse<>("Address deleted successfully", null));
	}
}
