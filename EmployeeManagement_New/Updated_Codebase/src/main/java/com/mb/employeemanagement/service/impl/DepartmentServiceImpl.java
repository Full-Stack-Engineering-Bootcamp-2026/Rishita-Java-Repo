package com.mb.employeemanagement.service.impl;

import com.mb.employeemanagement.service.DepartmentService;

import com.mb.employeemanagement.dto.request.DepartmentRequest;
import com.mb.employeemanagement.dto.response.DepartmentResponse;
import com.mb.employeemanagement.entity.Department;
import com.mb.employeemanagement.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repo;
    private final ModelMapper modelMapper;

    //create
    public DepartmentResponse create(DepartmentRequest request) {
        Department dept = modelMapper.map(request, Department.class);
        return modelMapper.map(repo.save(dept), DepartmentResponse.class);
    }

    //find by id
    public List<DepartmentResponse> findAll() {
        return repo.findAll().stream()
                 .map(dept -> modelMapper.map(dept, DepartmentResponse.class))
                 .collect(Collectors.toList());
    }

    //find by id
    public DepartmentResponse findById(int id) {
    	Department dept = repo.findById(id)
    		    .orElseThrow(() -> new RuntimeException("Department not found"));
        return modelMapper.map(dept, DepartmentResponse.class);
    }

    //update
    public DepartmentResponse update(int id, DepartmentRequest request) {
        Optional<Department> existing = repo.findById(id);

        if (existing.isPresent()) {
            Department dept = existing.get();
            dept.setName(request.getName());
            dept.setLocation(request.getLocation());
            dept.setHeadName(request.getHeadName());

            return modelMapper.map(repo.save(dept), DepartmentResponse.class);
        }

        return null;
    }

 //delete
    public void delete(int id) {
        repo.deleteById(id);
    }
}
