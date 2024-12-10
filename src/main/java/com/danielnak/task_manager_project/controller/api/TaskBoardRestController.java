package com.danielnak.task_manager_project.controller.api;

import com.danielnak.task_manager_project.model.Task;
import com.danielnak.task_manager_project.model.TaskBoard;
import com.danielnak.task_manager_project.service.TaskBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taskboards")
public class TaskBoardRestController {

    @Autowired
    private TaskBoardService taskBoardService;

    // Get all taskboards (e.g., List)
    @GetMapping
    public ResponseEntity<List<TaskBoard>> getAllTaskBoards() {
        List<TaskBoard> taskBoards = taskBoardService.getAllTaskBoards();
        return ResponseEntity.ok(taskBoards); // Return 200 OK with all taskboards
    }

    // Get a specific taskboard by ID (e.g., View details of a taskboard)
    @GetMapping("/{id}")
    public ResponseEntity<TaskBoard> getTaskBoardById(@PathVariable Long id) {
        TaskBoard taskBoard = taskBoardService.getTaskBoardById(id);
        if (taskBoard == null) {
            return ResponseEntity.notFound().build(); // Return 404 if taskboard not found
        }
        return ResponseEntity.ok(taskBoard);
    }

    // Create a new taskboard (e.g., Add a new taskboard)
    @PostMapping
    public ResponseEntity<TaskBoard> createTaskBoard(@RequestBody TaskBoard taskBoard) {
        TaskBoard createdTaskBoard = taskBoardService.saveTaskBoard(taskBoard);
        return ResponseEntity.ok(createdTaskBoard); // Return 200 OK with created taskboard details
    }

    // Update an existing taskboard (e.g., Modify its name)
    @PutMapping("/{id}")
    public ResponseEntity<TaskBoard> updateTaskBoard(@PathVariable Long id, @RequestBody TaskBoard updatedTaskBoard) {
        TaskBoard existingTaskBoard = taskBoardService.getTaskBoardById(id);
        if (existingTaskBoard == null) {
            return ResponseEntity.notFound().build(); // Return 404 if taskboard not found
        }
        existingTaskBoard.setName(updatedTaskBoard.getName());
        TaskBoard savedTaskBoard = taskBoardService.saveTaskBoard(existingTaskBoard);
        return ResponseEntity.ok(savedTaskBoard);
    }

    // Delete a taskboard
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskBoard(@PathVariable Long id) {
        TaskBoard taskBoard = taskBoardService.getTaskBoardById(id);
        if (taskBoard == null) {
            return ResponseEntity.notFound().build(); // Return 404 if taskboard not found
        }
        taskBoardService.saveTaskBoard(taskBoard);
        return ResponseEntity.noContent().build(); // Return 204 when taskboard is successfully deleted
    }

    // Add a task to a taskboard
    @PostMapping("/{taskBoardId}/tasks")
    public ResponseEntity<Task> addTaskToTaskBoard(@PathVariable Long taskBoardId, @RequestBody Task task) {
        try {
            Task createdTask = taskBoardService.addTaskToTaskBoard(taskBoardId, task);
            return ResponseEntity.ok(createdTask); // Return 200 OK with created task details
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Return 404 if taskboard not found
        }
    }
}