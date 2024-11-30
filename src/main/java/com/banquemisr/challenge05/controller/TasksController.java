package com.banquemisr.challenge05.controller;

import com.banquemisr.challenge05.dto.TaskDto;
import com.banquemisr.challenge05.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<?> createNewTask(@Valid @RequestBody TaskDto newTask , Authentication authentication) {

        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(newTask,authentication));
    }

    @GetMapping
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getSingleTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateSingleTask(@PathVariable Long id, @RequestBody TaskDto taskUpdateRequest) {
        return ResponseEntity.ok(taskService.updateTask(id, taskUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSingleTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task with id " + id + " deleted successfully.");
    }
}

/* todo : we use id which is db id for simplicity
 * todo : in real life we need to add task id which is unique and randomly generated
 * todo : in real life all response code should be localized and mapped via db or xml files
 *
 *
 * */