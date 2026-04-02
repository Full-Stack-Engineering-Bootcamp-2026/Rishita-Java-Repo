package com.mb.EmployeeManagementProject.service;

import com.mb.EmployeeManagementProject.model.*;
import com.mb.EmployeeManagementProject.repository.EmployeeRepository;
import com.mb.EmployeeManagementProject.repository.LeaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveService {

    private LeaveRepository leaveRepo;
    private EmployeeRepository employeeRepo;

    public LeaveService(LeaveRepository leaveRepo, EmployeeRepository employeeRepo) {
        this.leaveRepo = leaveRepo;
        this.employeeRepo = employeeRepo;
    }

    //create
    public Leave apply(Leave leave, int employeeId) {
        Optional<Employee> emp = employeeRepo.findById(employeeId);

        if (emp.isPresent()) {
            leave.setEmployee(emp.get());
            leave.setStatus(LeaveStatus.PENDING);
            return leaveRepo.save(leave);
        }

        return null;
    }
    
//get all
    public List<Leave> findAll() {
        return leaveRepo.findAll();
    }

  //get by id
    public Leave findById(int id) {
        return leaveRepo.findById(id).orElse(null);
    }

//find by emp id 
    public List<Leave> findByEmployeeId(int employeeId) {
        return leaveRepo.findByEmployeeId(employeeId);
    }

//delete
    public void delete(int id) {
        leaveRepo.deleteById(id);
    }
}