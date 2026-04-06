package com.mb.employeemanagement.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import com.mb.employeemanagement.validation.MindbowserEmail;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    @NotBlank(message = "Employee name cannot be blank")
    private String name;

    @MindbowserEmail
    private String email;

    @Min(value = 50000, message = "Salary cannot be less than 50000")
    private double salary;

}
