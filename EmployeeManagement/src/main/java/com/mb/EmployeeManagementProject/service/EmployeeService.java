package com.mb.EmployeeManagementProject.service;

import com.mb.EmployeeManagementProject.model.Department;
import com.mb.EmployeeManagementProject.model.Employee;
import com.mb.EmployeeManagementProject.repository.DepartmentRepository;
import com.mb.EmployeeManagementProject.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepo;
    private DepartmentRepository departmentRepo;

    public EmployeeService(EmployeeRepository employeeRepo, DepartmentRepository departmentRepo) {
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
    }
    
    //create
    public Employee create(Employee emp, int departmentId) {
        Optional<Department> dept = departmentRepo.findById(departmentId);

        if (dept.isPresent()) {
            emp.setDepartment(dept.get());
            return employeeRepo.save(emp);
        }

        return null;
    }

   //get all
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    	//get by id
    public Employee findById(int id) {
        return employeeRepo.findById(id).orElse(null);
    }

    public List<Employee> findByDepartmentName(String dept) {
        return employeeRepo.findByDepartmentName(dept);
    }

    //update
    public Employee update(int id, Employee updated, int departmentId) {
        Optional<Employee> existing = employeeRepo.findById(id);
        Optional<Department> dept = departmentRepo.findById(departmentId);

        if (existing.isPresent() && dept.isPresent()) {
            Employee emp = existing.get();

            emp.setName(updated.getName());
            emp.setEmail(updated.getEmail());
            emp.setSalary(updated.getSalary());
            emp.setDepartment(dept.get());

            return employeeRepo.save(emp);
        }

        return null;
    }

   //delete
    public void delete(int id) {
        employeeRepo.deleteById(id);
    }

   
    public long count() {
        return employeeRepo.count();
    }
}