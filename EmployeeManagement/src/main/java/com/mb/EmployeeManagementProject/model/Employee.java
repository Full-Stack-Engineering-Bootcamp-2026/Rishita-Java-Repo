package com.mb.EmployeeManagementProject.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mb.EmployeeManagementProject.validation.MindbowserEmail;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;


@Entity

@Table(name = "employee")
public class Employee {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 
    @NotBlank(message="Name cannot be blank")
    @Column(nullable = false)
    private String name;
 
    @MindbowserEmail
    @Column(nullable = false, unique = true)
    private String email;
 
    @Min(value = 50000, message = "Salary>50000")
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
 

    @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Project> projects;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}


	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public List<Leave> getLeaves() {
		return leaves;
	}


	public void setLeaves(List<Leave> leaves) {
		this.leaves = leaves;
	}


	public List<Project> getProjects() {
		return projects;
	}


	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}


	public Employee(int id, @NotBlank(message = "Name cannot be blank") String name, String email,
			@Min(value = 50000, message = "Salary>50000") double salary, Department department, Address address,
			List<Leave> leaves, List<Project> projects) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.salary = salary;
		this.department = department;
		this.address = address;
		this.leaves = leaves;
		this.projects = projects;
	}


	public Employee(@NotBlank(message = "Name cannot be blank") String name, String email,
			@Min(value = 50000, message = "Salary>50000") double salary, Department department) {
		super();
		this.name = name;
		this.email = email;
		this.salary = salary;
		this.department = department;
	}
	public Employee() {}
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

