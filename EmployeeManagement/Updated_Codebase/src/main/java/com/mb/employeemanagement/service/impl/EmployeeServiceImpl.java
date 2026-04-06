package com.mb.employeemanagement.service.impl;

import com.mb.employeemanagement.service.EmployeeService;

import com.mb.employeemanagement.dto.request.EmployeeRequest;
import com.mb.employeemanagement.dto.response.EmployeeResponse;
import com.mb.employeemanagement.entity.Department;
import com.mb.employeemanagement.entity.Employee;
import com.mb.employeemanagement.repository.DepartmentRepository;
import com.mb.employeemanagement.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepo;
	private final DepartmentRepository departmentRepo;
	private final ModelMapper modelMapper;

	// create
	// create
	public EmployeeResponse create(EmployeeRequest request, int departmentId) {
		Optional<Department> dept = departmentRepo.findById(departmentId);

		if (dept.isPresent()) {
            Employee emp = modelMapper.map(request, Employee.class);
			emp.setDepartment(dept.get());
			return modelMapper.map(employeeRepo.save(emp), EmployeeResponse.class);
		}

		throw new RuntimeException("Department not found with id: " + departmentId);
	}

	// get all
	public List<EmployeeResponse> findAll() {
		return employeeRepo.findAll().stream()
                .map(e -> modelMapper.map(e, EmployeeResponse.class))
                .collect(Collectors.toList());
	}

	// get by id
	public EmployeeResponse findById(int id) {
		Employee emp = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return modelMapper.map(emp, EmployeeResponse.class);
	}

	public List<EmployeeResponse> findByDepartmentName(String dept) {
		return employeeRepo.findByDepartmentNameIgnoreCase(dept).stream()
                .map(e -> modelMapper.map(e, EmployeeResponse.class))
                .collect(Collectors.toList());
	}

	// update
	// update
	public EmployeeResponse update(int id, EmployeeRequest request, int departmentId) {
		Optional<Employee> existing = employeeRepo.findById(id);
		Optional<Department> dept = departmentRepo.findById(departmentId);

		if (existing.isPresent() && dept.isPresent()) {
			Employee emp = existing.get();

			emp.setName(request.getName());
			emp.setEmail(request.getEmail());
			emp.setSalary(request.getSalary());
			emp.setDepartment(dept.get());

			return modelMapper.map(employeeRepo.save(emp), EmployeeResponse.class);
		}

		throw new RuntimeException("Department not found with id: " + departmentId);
	}

	// delete
	public void delete(int id) {
		if (!employeeRepo.existsById(id)) {
		    throw new RuntimeException("Employee not found");
		}
		employeeRepo.deleteById(id);
	}

	public long count() {
		return employeeRepo.count();
	}
}
