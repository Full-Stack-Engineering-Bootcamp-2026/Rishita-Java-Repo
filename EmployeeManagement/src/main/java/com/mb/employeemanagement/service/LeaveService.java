package com.mb.employeemanagement.service;

import com.mb.employeemanagement.dto.request.LeaveRequest;
import com.mb.employeemanagement.dto.response.LeaveResponse;
import com.mb.employeemanagement.enums.LeaveStatus;
import java.util.List;

public interface LeaveService {
    LeaveResponse apply(LeaveRequest request, int employeeId);
    List<LeaveResponse> findAll();
    LeaveResponse findById(int id);
    List<LeaveResponse> findByEmployeeId(int employeeId);
    void delete(int id);
    LeaveResponse updateStatus(int id, LeaveStatus status);
}
