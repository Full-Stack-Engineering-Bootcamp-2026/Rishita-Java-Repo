package com.mb.employeemanagement.service;

import com.mb.employeemanagement.dto.request.DepartmentRequest;
import com.mb.employeemanagement.dto.response.DepartmentResponse;
import java.util.List;

public interface DepartmentService {
    DepartmentResponse create(DepartmentRequest request);
    List<DepartmentResponse> findAll();
    DepartmentResponse findById(int id);
    DepartmentResponse update(int id, DepartmentRequest request);
    void delete(int id);
}
