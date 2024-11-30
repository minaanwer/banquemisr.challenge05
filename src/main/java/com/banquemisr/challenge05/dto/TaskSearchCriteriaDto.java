package com.banquemisr.challenge05.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskSearchCriteriaDto {
    private String title;
    private String description;
    private String status;
    private LocalDate dueDateFrom;
    private LocalDate dueDateTo;
}