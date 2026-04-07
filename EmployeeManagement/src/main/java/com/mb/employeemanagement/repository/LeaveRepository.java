package com.mb.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mb.employeemanagement.entity.Leave;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import java.util.Optional;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    @EntityGraph(attributePaths = {"employee", "approvedBy"})
    List<Leave> findByEmployeeId(Integer employeeId);
    
    @EntityGraph(attributePaths = {"employee", "approvedBy"})
    List<Leave> findAll();

    @EntityGraph(attributePaths = {"employee", "approvedBy"})
    Optional<Leave> findById(Integer id);
}
