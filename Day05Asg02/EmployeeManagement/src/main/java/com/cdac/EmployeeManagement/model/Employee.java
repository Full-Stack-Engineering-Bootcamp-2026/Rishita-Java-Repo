package com.cdac.EmployeeManagement.model;


import jakarta.validation.constraints.*;
public class Employee {

    private int id;
    
    @NotBlank(message = "Name cannot be empty")
    private String name;
    
    @NotBlank(message = "Name cannot be empty")
    private String department;
    
    @Email(message = "Invalid email format")
    private String email;
    
    @Min(value=25000, message="Salary must be greater than 25000")
    private double salary;

    public Employee() {}

    public Employee(int id, String name, String department, String email, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.email = email;
        this.salary = salary;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
}
