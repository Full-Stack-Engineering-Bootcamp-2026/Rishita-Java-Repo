package com.cdac.EmployeeManagement.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cdac.EmployeeManagement.model.Employee;
import com.cdac.EmployeeManagement.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    
    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable int id) {
        Employee emp = service.findById(id);
        if (emp == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(emp);
    }

    
    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee emp) {
        return ResponseEntity.status(201).body(service.save(emp));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable int id, @RequestBody Employee emp) {
        Employee updated = service.update(id, emp);
        if (updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (!service.delete(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> search(@RequestParam String department) {
        return ResponseEntity.ok(service.searchByDepartment(department));
    }

    
    @GetMapping("/searchBySalary")
    public ResponseEntity<List<Employee>> searchBySalary(@RequestParam double minSalary) {
        return ResponseEntity.ok(service.searchBySalary(minSalary));
    }


    @GetMapping("/count")
    public ResponseEntity<Integer> count() {
        return ResponseEntity.ok(service.count());
    }
}
