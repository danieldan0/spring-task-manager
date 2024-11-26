package com.danielnak.task_manager_project.controller;

import com.danielnak.task_manager_project.dto.ResponseMessage;
import com.danielnak.task_manager_project.model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    // Example: Create a new task
    @PostMapping
    public ResponseEntity<ResponseMessage> createTask(@RequestBody Task task) {
        // Logic to create a task
        return ResponseEntity.ok(new ResponseMessage("Task created successfully"));
    }

    // Example: Update a task
    @PutMapping("/{taskId}")
    public ResponseEntity<ResponseMessage> updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        // Logic to update a task
        return ResponseEntity.ok(new ResponseMessage("Task updated successfully"));
    }

    // Example: Delete a task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<ResponseMessage> deleteTask(@PathVariable Long taskId) {
        // Logic to delete a task
        return ResponseEntity.ok(new ResponseMessage("Task deleted successfully"));
    }
}

