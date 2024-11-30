package com.banquemisr.challenge05.controller;

import com.banquemisr.challenge05.dto.TaskDto;
import com.banquemisr.challenge05.model.TaskEntity;
import com.banquemisr.challenge05.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TasksControllerMockTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TasksController tasksController;

    private TaskDto dummyTask1;
    private TaskDto dummyTask2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dummyTask1 = new TaskDto();
        dummyTask1.setTitle("Task 1");
        dummyTask1.setDescription("Description 1");
        dummyTask1.setDueDate(LocalDate.now());
        dummyTask2 = new TaskDto();
        dummyTask2.setTitle("Task 2");
        dummyTask2.setDescription("Description 2");
        dummyTask2.setDueDate(LocalDate.now());
    }



    @Test
    void when_callGetAll_gotSuccessWithAvailableList() {
        List<TaskDto> tasks = Arrays.asList(dummyTask1, dummyTask2);
        when(taskService.getAllTasks()).thenReturn(tasks);

        List<TaskDto> response = tasksController.getAllTasks();

        assertEquals(2, response.size());
        assertEquals(dummyTask1, response.get(0));
        assertEquals(dummyTask2, response.get(1));
        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void when_callGetSingleTask_gotSuccessWithAvailableItem() {
        when(taskService.getTaskById(1L)).thenReturn(dummyTask1);

        ResponseEntity<TaskDto> response = tasksController.getSingleTaskById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dummyTask1, response.getBody());
        verify(taskService, times(1)).getTaskById(1L);
    }

    @Test
    void when_callUpdateTask_gotSuccessWithUpdatedItem() {
        TaskDto updatedTaskEntity = new TaskDto();
        updatedTaskEntity.setTitle("Updated Task");
        updatedTaskEntity.setDescription("Updated Description");
        updatedTaskEntity.setDueDate(LocalDate.now());
        when(taskService.updateTask(1L, updatedTaskEntity)).thenReturn(updatedTaskEntity);
        ResponseEntity< TaskDto> response = tasksController.updateSingleTask(1L, updatedTaskEntity);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedTaskEntity, response.getBody());
        verify(taskService, times(1)).updateTask(1L, updatedTaskEntity);
    }

    @Test
    void when_callDeleteTask_gotSuccess() {
        doNothing().when(taskService).deleteTask(1L);

        ResponseEntity<String> response = tasksController.deleteSingleTask(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(taskService, times(1)).deleteTask(1L);
    }
}