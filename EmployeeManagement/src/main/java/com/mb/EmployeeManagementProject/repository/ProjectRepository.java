package com.mb.EmployeeManagementProject.repository;

import com.mb.EmployeeManagementProject.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

	boolean existsByName(String name);

	@Query("SELECT p FROM Project p JOIN p.employees e WHERE e.id = :employeeId")
	List<Project> findByEmployeeId(@Param("employeeId") int employeeId);

}