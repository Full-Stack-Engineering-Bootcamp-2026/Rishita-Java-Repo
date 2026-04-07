package com.mb.employeemanagement.service.impl;

import com.mb.employeemanagement.service.ProjectService;

import com.mb.employeemanagement.dto.request.ProjectRequest;
import com.mb.employeemanagement.dto.response.ProjectResponse;
import com.mb.employeemanagement.entity.Project;
import org.springframework.stereotype.Service;
import com.mb.employeemanagement.repository.ProjectRepository;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepo;
    private final ModelMapper modelMapper;

   //create
    public ProjectResponse create(ProjectRequest request) {
        Project project = modelMapper.map(request, Project.class);
        return modelMapper.map(projectRepo.save(project), ProjectResponse.class);
    }

    //get all
    public List<ProjectResponse> findAll() {
        return projectRepo.findAll().stream()
                .map(p -> modelMapper.map(p, ProjectResponse.class))
                .collect(Collectors.toList());
    }

    //by id
    public ProjectResponse findById(int id) {
        Project p = projectRepo.findById(id).orElse(null);
        return p != null ? modelMapper.map(p, ProjectResponse.class) : null;
    }
 //byempid
    public List<ProjectResponse> findByEmployeeId(int employeeId) {
        return projectRepo.findByEmployeeProjects_Employee_Id(employeeId).stream()
                .map(p -> modelMapper.map(p, ProjectResponse.class))
                .collect(Collectors.toList());
    }

   //update
    public ProjectResponse update(int id, ProjectRequest request) {
        Optional<Project> existing = projectRepo.findById(id);

        if (existing.isPresent()) {
            Project proj = existing.get();

            proj.setName(request.getName());
            proj.setStartDate(request.getStartDate());
            proj.setEndDate(request.getEndDate());

            return modelMapper.map(projectRepo.save(proj), ProjectResponse.class);
        }

        return null;
    }
    //delete
    public void delete(int id) {
        projectRepo.deleteById(id);
    }
}
