package com.mb.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.mb.employeemanagement.entity.Project;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

	boolean existsByName(String name);

	@EntityGraph(attributePaths = {"employeeProjects"})
	List<Project> findByEmployeeProjects_Employee_Id(Integer employeeId);

    @EntityGraph(attributePaths = {"employeeProjects"})
    List<Project> findAll();

    @EntityGraph(attributePaths = {"employeeProjects"})
    Optional<Project> findById(Integer id);
}
