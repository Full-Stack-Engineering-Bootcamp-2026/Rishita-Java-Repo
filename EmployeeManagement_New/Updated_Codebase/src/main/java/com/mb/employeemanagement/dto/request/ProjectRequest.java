package com.mb.employeemanagement.dto.request;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {
    @NotBlank(message = "Project name cannot be blank")
    private String name;

    private LocalDate startDate;
    private LocalDate endDate;

}
