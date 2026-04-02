package com.mb.EmployeeManagementProject.repository;

import com.mb.EmployeeManagementProject.model.Leave;
import com.mb.EmployeeManagementProject.model.LeaveStatus;
import com.mb.EmployeeManagementProject.model.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    
    List<Leave> findByEmployeeId(int employeeId);
}