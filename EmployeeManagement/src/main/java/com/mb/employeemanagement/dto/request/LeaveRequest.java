package com.mb.employeemanagement.dto.request;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import com.mb.employeemanagement.enums.LeaveType;
import com.mb.employeemanagement.enums.LeaveStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest {
    @NotNull(message = "A valid leave type must be specified")
    private LeaveType leaveType;

    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private LeaveStatus status;

}
