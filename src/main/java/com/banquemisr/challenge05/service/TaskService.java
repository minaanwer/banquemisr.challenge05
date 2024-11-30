package com.banquemisr.challenge05.service;

import com.banquemisr.challenge05.dto.TaskDto;
import com.banquemisr.challenge05.dto.TaskSearchCriteriaDto;
import com.banquemisr.challenge05.mapper.TaskMapper;
import com.banquemisr.challenge05.util.Priority;
import com.banquemisr.challenge05.util.Status;
import com.banquemisr.challenge05.model.TaskEntity;
import com.banquemisr.challenge05.repo.TaskRepository;
import com.banquemisr.challenge05.repo.TaskSearchRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskSearchRepository taskSearchRepository;
    private final TaskMapper taskMapper;

    @Transactional
    public TaskDto createTask(TaskDto taskDto) {
        TaskEntity taskEntity = taskMapper.map(taskDto);
        TaskEntity savedTask = taskRepository.save(taskEntity);
        return taskMapper.map(savedTask);
    }

    public List<TaskDto> getAllTasks() {

        return taskMapper.map(taskRepository.findAll());
    }

    public TaskDto getTaskById(Long id) {
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        return taskMapper.map(task);
    }

    @Transactional
    public TaskDto updateTask(Long id, TaskDto taskUpdates) {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskEntity.setTitle(taskUpdates.getTitle());
        taskEntity.setDescription(taskUpdates.getDescription());
        taskEntity.setStatus(Status.valueOf(taskUpdates.getStatus()));
        taskEntity.setPriority(Priority.valueOf(taskUpdates.getPriority()));
        taskEntity.setDueDate(taskUpdates.getDueDate());

        taskEntity = taskRepository.save(taskEntity);
        return taskMapper.map(taskEntity);
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Page<TaskDto> searchTasks(TaskSearchCriteriaDto searchCriteria, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<TaskEntity> spec = Specification.where(null);

        if (searchCriteria.getTitle() != null) {
            spec = spec.and((root, query, builder) ->
                    builder.like(root.get("title"), "%" + searchCriteria.getTitle() + "%"));
        }
        if (searchCriteria.getDescription() != null) {
            spec = spec.and((root, query, builder) ->
                    builder.like(root.get("description"), "%" + searchCriteria.getDescription() + "%"));
        }
        if (searchCriteria.getStatus() != null) {
            spec = spec.and((root, query, builder) ->
                    builder.equal(root.get("status"), Enum.valueOf(Status.class,searchCriteria.getStatus())));
        }
        if (searchCriteria.getDueDateFrom() != null) {
            spec = spec.and((root, query, builder) ->
                    builder.greaterThanOrEqualTo(root.get("dueDate"), searchCriteria.getDueDateFrom()));
        }
        if (searchCriteria.getDueDateTo() != null) {
            spec = spec.and((root, query, builder) ->
                    builder.lessThanOrEqualTo(root.get("dueDate"), searchCriteria.getDueDateTo()));
        }
        Page<TaskEntity> taskEntities = taskSearchRepository.findAll(spec, pageable);
        return taskEntities.map(taskMapper::map);
    }
}
