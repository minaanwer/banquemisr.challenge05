package com.banquemisr.challenge05.repo;

import com.banquemisr.challenge05.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByDueDateBetween(LocalDate startDate, LocalDate endDate);
}