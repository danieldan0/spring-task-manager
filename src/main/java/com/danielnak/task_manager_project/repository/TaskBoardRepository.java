package com.danielnak.task_manager_project.repository;

import com.danielnak.task_manager_project.model.TaskBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskBoardRepository extends JpaRepository<TaskBoard, Long> {
    List<TaskBoard> findByUserId(Long userId);
}

