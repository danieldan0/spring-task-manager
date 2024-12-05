package com.danielnak.task_manager_project.repository;

import com.danielnak.task_manager_project.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTaskBoardId(Long taskBoardId);
    List<Task> findByStatus(String status);
}

