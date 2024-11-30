package com.banquemisr.challenge05.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    @NotBlank(message = "title is required.")
    @Size(max = 100, message = "Title must not exceed 100 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9_\\-., ]*$", message = "Only _, ., - are allowed in title")
    private String title;

    @NotBlank(message = "description is required.")
    @Size(max = 255, min = 1, message = "Description must not exceed 255 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9_\\-., ]*$", message = "Only _, ., - are allowed in description")
    private String description;

    @NotBlank(message = "status is required.")
    @Pattern(
            regexp = "^(TODO|IN_PROGRESS|DONE)$",
            message = "Status must be one of the following: TODO, IN_PROGRESS, DONE"
    )
    private String status;

    @Pattern(
            regexp = "^(LOW|MEDIUM|HIGH)$",
            message = "Priority must be one of the following: LOW, MEDIUM, HIGH"
    )
    private String priority;

    @JsonFormat(pattern = "yyyy-MM-dd") // This annotation ensures proper parsing of the date format
    private LocalDate dueDate;

    private long Id;
}
