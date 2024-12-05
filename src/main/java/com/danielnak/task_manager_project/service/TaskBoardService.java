package com.danielnak.task_manager_project.service;

import com.danielnak.task_manager_project.model.TaskBoard;
import com.danielnak.task_manager_project.repository.TaskBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskBoardService {

    private final TaskBoardRepository taskBoardRepository;

    @Autowired
    public TaskBoardService(TaskBoardRepository taskBoardRepository) {
        this.taskBoardRepository = taskBoardRepository;
    }

    public TaskBoard createBoard(TaskBoard taskBoard) {
        return taskBoardRepository.save(taskBoard);
    }

    public TaskBoard getBoardById(Long id) {
        return taskBoardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Board not found"));
    }

    public List<TaskBoard> getAllBoards() {
        return taskBoardRepository.findAll();
    }

    public TaskBoard updateBoard(Long id, TaskBoard updatedTaskBoard) {
        TaskBoard existingBoard = getBoardById(id);
        existingBoard.setName(updatedTaskBoard.getName());
        existingBoard.setDescription(updatedTaskBoard.getDescription());
        return taskBoardRepository.save(existingBoard);
    }

    public void deleteBoard(Long id) {
        taskBoardRepository.deleteById(id);
    }
}

