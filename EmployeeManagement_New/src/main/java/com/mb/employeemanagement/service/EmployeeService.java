package com.mb.employeemanagement.service;

import com.mb.employeemanagement.dto.request.EmployeeRequest;
import com.mb.employeemanagement.dto.response.EmployeeResponse;
import java.util.List;

public interface EmployeeService {
    EmployeeResponse create(EmployeeRequest request, int departmentId);
    List<EmployeeResponse> findAll();
    EmployeeResponse findById(int id);
    List<EmployeeResponse> findByDepartmentName(String dept);
    EmployeeResponse update(int id, EmployeeRequest request, int departmentId);
    void delete(int id);
    long count();
}
