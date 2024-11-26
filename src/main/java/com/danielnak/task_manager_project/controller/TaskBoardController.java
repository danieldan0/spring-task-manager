package com.danielnak.task_manager_project.controller;

import com.danielnak.task_manager_project.dto.ResponseMessage;
import com.danielnak.task_manager_project.model.TaskBoard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
public class TaskBoardController {

    // Example: Create a new task board
    @PostMapping
    public ResponseEntity<ResponseMessage> createBoard(@RequestBody TaskBoard taskBoard) {
        // Logic to create a new board
        return ResponseEntity.ok(new ResponseMessage("Task board created successfully"));
    }

    // Example: Get task board by ID
    @GetMapping("/{boardId}")
    public ResponseEntity<TaskBoard> getBoard(@PathVariable Long boardId) {
        // Logic to fetch a board by ID
        TaskBoard board = new TaskBoard("Sample Board", "Sample Description", true);
        return ResponseEntity.ok(board);
    }

    // Example: Delete task board
    @DeleteMapping("/{boardId}")
    public ResponseEntity<ResponseMessage> deleteBoard(@PathVariable Long boardId) {
        // Logic to delete a board
        return ResponseEntity.ok(new ResponseMessage("Task board deleted successfully"));
    }
}
