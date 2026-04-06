package com.mb.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mb.employeemanagement.entity.Department;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @EntityGraph(attributePaths = {"employees"})
    List<Department> findAll();

    @EntityGraph(attributePaths = {"employees"})
    Optional<Department> findById(Integer id);
}
