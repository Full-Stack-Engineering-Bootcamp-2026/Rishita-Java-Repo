package com.mb.employeemanagement.service.impl;

import com.mb.employeemanagement.service.LeaveService;

import com.mb.employeemanagement.dto.request.LeaveRequest;
import com.mb.employeemanagement.dto.response.LeaveResponse;
import com.mb.employeemanagement.entity.*;
import com.mb.employeemanagement.enums.LeaveStatus;
import com.mb.employeemanagement.exception.InvalidLeaveException;
import com.mb.employeemanagement.repository.EmployeeRepository;
import com.mb.employeemanagement.repository.LeaveRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

	private final LeaveRepository leaveRepo;
	private final EmployeeRepository employeeRepo;
	private final ModelMapper modelMapper;

	// create
	public LeaveResponse apply(LeaveRequest request, int employeeId) {
		Optional<Employee> emp = Optional.ofNullable(employeeRepo.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found")));

		if (request.getStartDate().isAfter(request.getEndDate())) {
			throw new InvalidLeaveException("Start date cannot be after end date");
		}
		if (emp.isPresent()) {
            Leave leave = modelMapper.map(request, Leave.class);
			leave.setEmployee(emp.get());
			leave.setStatus(LeaveStatus.PENDING);
			return modelMapper.map(leaveRepo.save(leave), LeaveResponse.class);
		}

		return null;
	}

//get all
	public List<LeaveResponse> findAll() {
		return leaveRepo.findAll().stream()
                .map(l -> modelMapper.map(l, LeaveResponse.class))
                .collect(Collectors.toList());
	}

	// get by id
	public LeaveResponse findById(int id) {
		Leave leave = leaveRepo.findById(id).orElse(null);
        return leave != null ? modelMapper.map(leave, LeaveResponse.class) : null;
	}

//find by emp id 
	public List<LeaveResponse> findByEmployeeId(int employeeId) {
		return leaveRepo.findByEmployeeId(employeeId).stream()
                .map(l -> modelMapper.map(l, LeaveResponse.class))
                .collect(Collectors.toList());
	}

//delete
	public void delete(int id) {
		leaveRepo.deleteById(id);
	}

	public LeaveResponse updateStatus(int id, LeaveStatus status) {
		Leave existing = leaveRepo.findById(id).orElseThrow(() -> new RuntimeException("Leave not found"));
		existing.setStatus(status);
		return modelMapper.map(leaveRepo.save(existing), LeaveResponse.class);
	}
}
