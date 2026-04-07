package com.mb.employeemanagement.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mb.employeemanagement.entity.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@EntityGraph(attributePaths = {"department", "address"})
	List<Employee> findByDepartmentNameIgnoreCase(String dept);
	
	@EntityGraph(attributePaths = {"department", "address"})
	List<Employee> findAll();

	@EntityGraph(attributePaths = {"department", "address"})
	Optional<Employee> findById(Integer id);
}
