package com.danielnak.task_manager_project.service;

import com.danielnak.task_manager_project.model.Task;
import com.danielnak.task_manager_project.model.TaskBoard;
import com.danielnak.task_manager_project.repository.TaskBoardRepository;
import com.danielnak.task_manager_project.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskBoardService {

    @Autowired
    private TaskBoardRepository taskBoardRepository;

    @Autowired
    private TaskRepository taskRepository;

    // Get all taskboards
    public List<TaskBoard> getAllTaskBoards() {
        return taskBoardRepository.findAll();
    }

    // Get a taskboard by ID
    public TaskBoard getTaskBoardById(Long id) {
        return taskBoardRepository.findById(id).orElse(null);
    }

    // Get all taskboards by user ID
    public List<TaskBoard> getTaskBoardsByUserId(Long userId) {
        return taskBoardRepository.findByUserId(userId);
    }

    // Create or update a taskboard
    public TaskBoard saveTaskBoard(TaskBoard taskBoard) {
        return taskBoardRepository.save(taskBoard);
    }

    // Add a task to a taskboard
    public Task addTaskToTaskBoard(Long taskBoardId, Task task) {
        TaskBoard taskBoard = taskBoardRepository.findById(taskBoardId).orElseThrow(() ->
                new RuntimeException("TaskBoard not found"));

        task.setTaskBoard(taskBoard);
        return taskRepository.save(task);
    }
}

