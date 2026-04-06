package com.mb.employeemanagement.dto.response;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {
    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

}
