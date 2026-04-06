package com.mb.EmployeeManagementProject.service;

import com.mb.EmployeeManagementProject.model.Department;
import com.mb.EmployeeManagementProject.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private DepartmentRepository repo;

    public DepartmentService(DepartmentRepository repo) {
        this.repo = repo;
    }

    //create
    public Department create(Department dept) {
        return repo.save(dept);
    }

    //find by id
    public List<Department> findAll() {
        return repo.findAll();
    }

    //find by id
    public Department findById(int id) {
        return repo.findById(id).orElse(null);
    }

    //update
    public Department update(int id, Department updated) {
        Optional<Department> existing = repo.findById(id);

        if (existing.isPresent()) {
            Department dept = existing.get();
            dept.setName(updated.getName());
            dept.setLocation(updated.getLocation());
            dept.setHeadName(updated.getHeadName());

            return repo.save(dept);
        }

        return null;
    }

 //delete
    public void delete(int id) {
        repo.deleteById(id);
    }
}