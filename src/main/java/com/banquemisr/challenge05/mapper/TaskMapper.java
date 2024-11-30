package com.banquemisr.challenge05.mapper;


import com.banquemisr.challenge05.dto.TaskDto;
import com.banquemisr.challenge05.util.Priority;
import com.banquemisr.challenge05.util.Status;
import com.banquemisr.challenge05.model.TaskEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    public List<TaskDto> map(List<TaskEntity> all) {
        return all.stream()
                .map(this::map) // Using the single entity mapping method
                .collect(Collectors.toList());
    }

    public TaskEntity map(TaskDto taskDto) {
        if (taskDto == null) {
            return null;
        }
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(taskDto.getTitle());
        taskEntity.setDescription(taskDto.getDescription());
        if (taskDto.getStatus() != null) {
            taskEntity.setStatus(Status.valueOf(taskDto.getStatus()));
        }
        taskEntity.setDueDate(taskDto.getDueDate());
        if (taskDto.getPriority() != null) {
            taskEntity.setPriority(Priority.valueOf(taskDto.getPriority()));
        }
        // taskEntity.setUser(user); // Assuming the User is handled separately
        return taskEntity;
    }

    public TaskDto map(TaskEntity taskEntity) {
        if (taskEntity == null) {
            return null;
        }
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle(taskEntity.getTitle());
        taskDto.setDescription(taskEntity.getDescription());
        if (taskEntity.getStatus() != null) {
            taskDto.setStatus(taskEntity.getStatus().name());
        }
        if (taskEntity.getPriority() != null) {
            taskDto.setPriority(taskEntity.getPriority().name());
        }
        taskDto.setDueDate(taskEntity.getDueDate());
        taskDto.setId(taskEntity.getId());
        return taskDto;
    }
}

