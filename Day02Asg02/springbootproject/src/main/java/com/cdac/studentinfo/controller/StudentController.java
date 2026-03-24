package com.cdac.studentinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.studentinfo.model.Student;
import com.cdac.studentinfo.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@Value("${app.batch.name}")
	private String batchName;
	
	 @GetMapping("/students")
	    public List<Student> getStudents(@RequestParam(required = false) String batch) {
	        List<Student> students = service.getAllStudents();

	        if (batch != null) {
	            return students.stream()
	                    .filter(s -> s.getBatch().equals(batch))
	                    .toList();
	        }

	        return students;
	    }

	    @GetMapping("/students/{id}")
	    public Student getStudent(@PathVariable int id) {
	        return service.getStudentById(id);
	    }

	    @GetMapping("/batch")
	    public String getBatch() {
	        return batchName;
	    }
	
}
