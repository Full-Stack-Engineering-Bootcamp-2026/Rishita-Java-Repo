package com.mb.employeemanagement.service;

import com.mb.employeemanagement.dto.request.ProjectRequest;
import com.mb.employeemanagement.dto.response.ProjectResponse;
import java.util.List;

public interface ProjectService {
    ProjectResponse create(ProjectRequest request);
    List<ProjectResponse> findAll();
    ProjectResponse findById(int id);
    List<ProjectResponse> findByEmployeeId(int employeeId);
    ProjectResponse update(int id, ProjectRequest request);
    void delete(int id);
}
