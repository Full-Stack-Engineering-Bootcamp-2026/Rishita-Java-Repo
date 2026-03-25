package com.cdac.EmployeeManagement.service;


import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cdac.EmployeeManagement.model.Employee;

@Service
public class EmployeeService {

    private Map<Integer, Employee> store = new HashMap<>();

    public EmployeeService() {
       
        store.put(1, new Employee(1, "Rishita", "Engineering", "rishita@gmail.com", 70000));
        store.put(2, new Employee(2, "Amit", "HR", "amit@gmail.com", 50000));
        store.put(3, new Employee(3, "Neha", "Engineering", "neha@gmail.com", 80000));
        store.put(4, new Employee(4, "Raj", "Finance", "raj@gmail.com", 60000));
        store.put(5, new Employee(5, "Simran", "Engineering", "simran@gmail.com", 75000));
    }

    public List<Employee> findAll() {
        return new ArrayList<>(store.values());
    }

    public Employee findById(int id) {
        return store.get(id);
    }

    public Employee save(Employee emp) {
        store.put(emp.getId(), emp);
        return emp;
    }

    public Employee update(int id, Employee emp) {
        if (!store.containsKey(id)) return null;
        emp.setId(id);
        store.put(id, emp);
        return emp;
    }

    public boolean delete(int id) {
        return store.remove(id) != null;
    }

    public List<Employee> searchByDepartment(String dept) {
        return store.values()
                .stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase(dept))
                .collect(Collectors.toList());
    }


    public List<Employee> searchBySalary(double minSalary) {
        return store.values()
                .stream()
                .filter(e -> e.getSalary() >= minSalary)
                .toList();
    }

    public int count() {
        return store.size();
    }
}
