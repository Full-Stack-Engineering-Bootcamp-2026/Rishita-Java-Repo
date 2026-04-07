package com.mb.employeemanagement.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.mb.employeemanagement.enums.LeaveStatus;
import com.mb.employeemanagement.enums.LeaveType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "leaves")
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Leave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id", nullable = false)

	private Employee employee;

	@Enumerated(EnumType.STRING)
	@Column(name = "leave_type", nullable = false)

	private LeaveType leaveType;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private LeaveStatus status = LeaveStatus.PENDING;

	private String reason;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approved_by")
	private Employee approvedBy;

	@Column(name = "applied_on")
	private LocalDateTime appliedOn = LocalDateTime.now();


}
