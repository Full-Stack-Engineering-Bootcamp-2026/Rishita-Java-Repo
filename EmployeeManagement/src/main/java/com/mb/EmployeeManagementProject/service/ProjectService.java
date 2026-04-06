package com.mb.EmployeeManagementProject.service;

import com.mb.EmployeeManagementProject.model.Employee;
import com.mb.EmployeeManagementProject.model.Project;
import com.mb.EmployeeManagementProject.repository.EmployeeRepository;
import com.mb.EmployeeManagementProject.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private ProjectRepository projectRepo;
    private EmployeeRepository employeeRepo;

    public ProjectService(ProjectRepository projectRepo, EmployeeRepository employeeRepo) {
        this.projectRepo = projectRepo;
        this.employeeRepo = employeeRepo;
    }

   //create
    public Project create(Project project) {
        return projectRepo.save(project);
    }

    //get all
    public List<Project> findAll() {
        return projectRepo.findAll();
    }

    //by id
    public Project findById(int id) {
        return projectRepo.findById(id).orElse(null);
    }
 //byempid
    public List<Project> findByEmployeeId(int employeeId) {
        return projectRepo.findByEmployeeId(employeeId);
    }

   //update
    public Project update(int id, Project updated) {
        Optional<Project> existing = projectRepo.findById(id);

        if (existing.isPresent()) {
            Project proj = existing.get();

            proj.setName(updated.getName());
            proj.setStartDate(updated.getStartDate());
            proj.setEndDate(updated.getEndDate());

            return projectRepo.save(proj);
        }

        return null;
    }
    //delete
    public void delete(int id) {
        projectRepo.deleteById(id);
    }
}