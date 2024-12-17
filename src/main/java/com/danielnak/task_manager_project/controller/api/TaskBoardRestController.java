package com.danielnak.task_manager_project.controller.api;

import com.danielnak.task_manager_project.dto.TaskBoardDto;
import com.danielnak.task_manager_project.dto.TaskDto;
import com.danielnak.task_manager_project.model.Task;
import com.danielnak.task_manager_project.model.TaskBoard;
import com.danielnak.task_manager_project.service.TaskBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/taskboards")
public class TaskBoardRestController {

    @Autowired
    private TaskBoardService taskBoardService;

    // Get all taskboards (e.g., List)
    @GetMapping
    public ResponseEntity<List<TaskBoardDto>> getAllTaskBoards() {
        List<TaskBoardDto> taskBoards = taskBoardService.getAllTaskBoards().stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(taskBoards); // Return 200 OK with all taskboards
    }

    // Get a specific taskboard by ID (e.g., View details of a taskboard)
    @GetMapping("/{id}")
    public ResponseEntity<TaskBoardDto> getTaskBoardById(@PathVariable Long id) {
        TaskBoardDto taskBoard = convertToDto(taskBoardService.getTaskBoardById(id));
        if (taskBoard == null) {
            return ResponseEntity.notFound().build(); // Return 404 if taskboard not found
        }
        return ResponseEntity.ok(taskBoard);
    }

    // Create a new taskboard (e.g., Add a new taskboard)
    @PostMapping
    public ResponseEntity<TaskBoardDto> createTaskBoard(@RequestBody TaskBoardDto taskBoardDto) {
        TaskBoard taskBoard = convertToEntity(taskBoardDto);
        TaskBoard createdTaskBoard = taskBoardService.saveTaskBoard(taskBoard);
        return ResponseEntity.ok(convertToDto(createdTaskBoard)); // Return 200 OK with created taskboard details
    }

    // Update an existing taskboard (e.g., Modify its name)
    @PutMapping("/{id}")
    public ResponseEntity<TaskBoardDto> updateTaskBoard(@PathVariable Long id, @RequestBody TaskBoardDto updatedTaskBoardDto) {
        TaskBoard existingTaskBoard = taskBoardService.getTaskBoardById(id);
        if (existingTaskBoard == null) {
            return ResponseEntity.notFound().build(); // Return 404 if taskboard not found
        }
        TaskBoard updatedTaskBoard = convertToEntity(updatedTaskBoardDto);
        existingTaskBoard.setName(updatedTaskBoard.getName());
        TaskBoard savedTaskBoard = taskBoardService.saveTaskBoard(existingTaskBoard);
        return ResponseEntity.ok(convertToDto(savedTaskBoard));
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
    public ResponseEntity<TaskDto> addTaskToTaskBoard(@PathVariable Long taskBoardId, @RequestBody TaskDto taskDto) {
        try {
            Task task = convertToEntity(taskDto);
            Task createdTask = taskBoardService.addTaskToTaskBoard(taskBoardId, task);
            return ResponseEntity.ok(convertToDto(createdTask)); // Return 200 OK with created task details
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Return 404 if taskboard not found
        }
    }

    private TaskBoardDto convertToDto(TaskBoard taskBoard) {
        TaskBoardDto taskBoardDto = new TaskBoardDto();
        taskBoardDto.setId(taskBoard.getId());
        taskBoardDto.setName(taskBoard.getName());
        taskBoardDto.setTasks(taskBoard.getTasks().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
        return taskBoardDto;
    }

    private TaskDto convertToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setStatus(task.getStatus());
        taskDto.setDueDate(task.getDueDate());
        return taskDto;
    }

    private TaskBoard convertToEntity(TaskBoardDto taskBoardDto) {
        TaskBoard taskBoard = new TaskBoard();
        taskBoard.setId(taskBoardDto.getId());
        taskBoard.setName(taskBoardDto.getName());
        taskBoard.setTasks(taskBoardDto.getTasks() != null ? taskBoardDto.getTasks().stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList()) : new ArrayList<>());
        return taskBoard;
    }

    private Task convertToEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setDueDate(taskDto.getDueDate());
        return task;
    }
}