package com.mb.EmployeeManagementProject.repository;

import com.mb.EmployeeManagementProject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Query("SELECT e FROM Employee e WHERE LOWER(e.department.name) = LOWER(:dept)")
	List<Employee> findByDepartmentName(@Param("dept") String dept);



}