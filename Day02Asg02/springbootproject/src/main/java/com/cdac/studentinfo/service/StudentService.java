package com.cdac.studentinfo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cdac.studentinfo.model.Student;

@Service
public class StudentService {

    public List<Student> getAllStudents() {
        return Arrays.asList(
            new Student(1, "Rishita", "CDAC-2026", "rishita@gmail.com"),
            new Student(2, "Amit", "CDAC-2026", "amit@gmail.com"),
            new Student(3, "Neha", "CDAC-2025", "neha@gmail.com")
        );
    }

    public Student getStudentById(int id) {
        return getAllStudents()
                .stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
