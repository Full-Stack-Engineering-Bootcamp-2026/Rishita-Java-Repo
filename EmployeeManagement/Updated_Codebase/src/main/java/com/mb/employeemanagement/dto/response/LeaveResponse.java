package com.mb.employeemanagement.dto.response;

import java.time.LocalDate;
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
public class LeaveResponse {
    private int id;
    private LeaveType leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private LeaveStatus status;

}
