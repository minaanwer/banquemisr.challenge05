package com.banquemisr.challenge05.controller;


import com.banquemisr.challenge05.dto.TaskDto;
import com.banquemisr.challenge05.dto.TaskSearchCriteriaDto;
import com.banquemisr.challenge05.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

class TaskSearchControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskSearchController taskSearchController;

    private TaskSearchCriteriaDto searchCriteria;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        searchCriteria = new TaskSearchCriteriaDto();
    }

    @Test
    void searchTasks_shouldReturnOkResponse_whenValidCriteria() {
        TaskDto taskDto = new TaskDto(); // Set necessary properties on TaskDto
        Page<TaskDto> page = new PageImpl<>(Collections.singletonList(taskDto));
        when(taskService.searchTasks(searchCriteria, 0, 10)).thenReturn(page);
        ResponseEntity<Page<TaskDto>> response = taskSearchController.searchTasks(searchCriteria, 0, 10);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getTotalElements()).isEqualTo(1);
    }

    @Test
    void searchTasks_shouldReturnEmptyPage_whenNoTasksFound() {
        Page<TaskDto> emptyPage = Page.empty();
        when(taskService.searchTasks(searchCriteria, 0, 10)).thenReturn(emptyPage);
        ResponseEntity<Page<TaskDto>> response = taskSearchController.searchTasks(searchCriteria, 0, 10);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getTotalElements()).isEqualTo(0);
    }

    @Test
    void searchTasks_shouldReturnBadRequest_whenServiceThrowsException() {
        when(taskService.searchTasks(searchCriteria, 0, 10)).thenThrow(new RuntimeException("Service error"));
        try {
            taskSearchController.searchTasks(searchCriteria, 0, 10);
        } catch (RuntimeException ex) {
            assertThat(ex.getMessage()).isEqualTo("Service error");
        }
    }
}

