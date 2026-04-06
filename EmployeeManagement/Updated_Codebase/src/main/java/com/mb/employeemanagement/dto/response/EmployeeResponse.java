package com.mb.employeemanagement.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private int id;
    private String name;
    private String email;
    private double salary;
    private String departmentName;

}
