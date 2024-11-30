package com.banquemisr.challenge05.controller;

import com.banquemisr.challenge05.dto.TaskDto;
import com.banquemisr.challenge05.dto.TaskSearchCriteriaDto;
import com.banquemisr.challenge05.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskSearchController {

    private final TaskService taskService;


    @PostMapping("/search")
    public ResponseEntity<Page<TaskDto>> searchTasks(
            @RequestBody TaskSearchCriteriaDto searchCriteria,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Page<TaskDto> tasks = taskService.searchTasks(searchCriteria, page, size);
        return ResponseEntity.ok(tasks);
    }
}
