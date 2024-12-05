package com.danielnak.task_manager_project.repository;

import com.danielnak.task_manager_project.model.TaskBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskBoardRepository extends JpaRepository<TaskBoard, Long> {
}

