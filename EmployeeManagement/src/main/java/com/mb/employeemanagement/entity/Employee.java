package com.mb.employeemanagement.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mb.employeemanagement.validation.MindbowserEmail;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "employees")
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 

    @Column(nullable = false)
    private String name;
 
    @MindbowserEmail
    @Column(nullable = false, unique = true)
    private String email;
 

    @Column(nullable = false)
    private double salary;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;
 

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Address address;
 

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Leave> leaves;
 

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<EmployeeProject> employeeProjects;



}
//public class Employee {
//	private int id;
//	
//	@NotBlank(message="Name cannot be empty")
//	private String name;
//	
//	@NotBlank(message="dept cannot be blank")
//	private String department;
//	
//	@Email(message="invalid email format")
//	private String email;
//	
//	@Min(value=50000)
//	private double salary;
//	
//}

