package com.banquemisr.challenge05.controller;



import com.banquemisr.challenge05.dto.TaskDto;
import com.banquemisr.challenge05.dto.TaskSearchCriteriaDto;
import com.banquemisr.challenge05.model.TaskEntity;
import com.banquemisr.challenge05.repo.TaskRepository;
import com.banquemisr.challenge05.service.TaskService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskSearchControllerRealTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    private final String bearerToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJub3JtYWxfdXNlciIsImlhdCI6MTczMzAwMTU3NSwiZXhwIjozNjAwMDE3MzMwMDE1NzV9.Clo4wzDLK6wOPP1dxM3ZtyuDuGXObBCn0gHSWJSRNSq5mpgskQRprIS1HxGwYZ2MbUWBpSkLijJjh_3Y1p0Ctg";

    @BeforeEach
    void setUp() {
        // Insert some test data into the repository
        taskRepository.deleteAll();  // Clear previous data
        taskRepository.save(new TaskEntity("Test Task 1", "Description of task 1"));
        taskRepository.save(new TaskEntity("Test Task 2", "Description of task 2"));
    }

    @Test
    void searchTasks_shouldReturnTasks_whenValidCriteria() throws Exception {
        // Arrange
        TaskSearchCriteriaDto searchCriteria = new TaskSearchCriteriaDto();
        searchCriteria.setTitle("Test Task");

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tasks/search")
                        .contentType("application/json")
                        .content("{\"taskName\":\"Test Task\"}")
                        .header("Authorization", "Bearer " + bearerToken))  // Add the Bearer token
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].title").value("Test Task 1"));
    }

    @Test
    void searchTasks_shouldReturnEmpty_whenNoTasksMatch() throws Exception {
        // Arrange
        TaskSearchCriteriaDto searchCriteria = new TaskSearchCriteriaDto();
        searchCriteria.setTitle("Non-existent Task");

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tasks/search")
                        .contentType("application/json")
                        .content("{\"taskName\":\"Non-existent Task\"}")
                        .header("Authorization", "Bearer " + bearerToken))  // Add the Bearer token
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(2));
    }

    @Test
    void searchTasks_shouldReturnTasksWithPagination_whenPaginated() throws Exception {
        // Arrange
        TaskSearchCriteriaDto searchCriteria = new TaskSearchCriteriaDto();
        searchCriteria.setTitle("Test Task");

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tasks/search")
                        .param("page", "0")
                        .param("size", "1")
                        .contentType("application/json")
                        .content("{\"taskName\":\"Test Task\"}")
                        .header("Authorization", "Bearer " + bearerToken))  // Add the Bearer token
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(1));
    }
}
