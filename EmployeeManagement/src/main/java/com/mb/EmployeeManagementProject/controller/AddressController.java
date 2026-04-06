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
import com.mb.EmployeeManagementProject.model.Address;
import com.mb.EmployeeManagementProject.service.AddressService;

@Validated
@RestController
@RequestMapping(value = "/addresses", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class AddressController {

	private final AddressService service;

	public AddressController(AddressService service) {
		this.service = service;
	}
	
	//get all

	@GetMapping
	public ResponseEntity<ApiResponse<List<Address>>> getAll() {
		return ResponseEntity.ok(new ApiResponse<>("All addresses", service.findAll()));
	}
	
	 //get by id

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Address>> getById(@PathVariable @Min(value = 1, message = "ID mustbe positive") int id) {
		return ResponseEntity.ok(new ApiResponse<>("Address:", service.findById(id)));
	}

	//add by emp id
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<ApiResponse<Address>> getByEmployeeId(@PathVariable @Min(value = 1, message = "employeeId mustbe +ve") int employeeId) {
		return ResponseEntity.ok(new ApiResponse<>("Address for employee " + employeeId, service.findByEmployeeId(employeeId)));
	}
	
	//create

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ApiResponse<Address>> create(@Valid @RequestBody Address address,@RequestParam @Min(value = 1, message = "employeeId mustbe +ve") int employeeId) {

		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Address created", service.create(address, employeeId)));
	}

	//update
	@PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ApiResponse<Address>> update(@PathVariable @Min(value = 1, message = "ID mustbe +be") int id, @Valid @RequestBody Address address) {

		return ResponseEntity.ok(new ApiResponse<>("Address updated", service.update(id, address)));
	}

	//delete
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable @Min(value = 1, message = "ID must be >= 1") int id) {

		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}