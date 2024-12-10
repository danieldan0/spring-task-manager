package com.danielnak.task_manager_project.controller;

import com.danielnak.task_manager_project.model.Task;
import com.danielnak.task_manager_project.model.TaskBoard;
import com.danielnak.task_manager_project.model.User;
import com.danielnak.task_manager_project.repository.UserRepository;
import com.danielnak.task_manager_project.service.TaskBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/taskboards")
public class TaskBoardController {

    @Autowired
    private TaskBoardService taskBoardService;

    @Autowired
    private UserRepository userRepository;

    // Show a single taskboard with its tasks
    @GetMapping("/{id}")
    public String showTaskBoard(@PathVariable Long id, Model model) {
        TaskBoard taskBoard = taskBoardService.getTaskBoardById(id);
        model.addAttribute("taskBoard", taskBoard);
        return "task_board"; // Points to "taskboard.html"
    }

    // Create a new taskboard form
    @GetMapping("/new")
    public String showNewTaskBoardForm(Model model) {
        model.addAttribute("taskBoard", new TaskBoard());
        return "new_task_board"; // Points to "new-taskboard.html"
    }

    // Save a new taskboard
    @PostMapping
    public String saveTaskBoard(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute TaskBoard taskBoard) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElse(null);

        if (user != null) {
            taskBoard.setUser(user);
            taskBoardService.saveTaskBoard(taskBoard);
        }

        return "redirect:/home";
    }

    // Add a new task to a taskboard
    @PostMapping("/{id}/tasks")
    public String addTaskToTaskBoard(@PathVariable Long id, @RequestParam String taskTitle, @RequestParam String taskStatus) {
        Task task = new Task();
        task.setTitle(taskTitle);
        task.setStatus(taskStatus);
        taskBoardService.addTaskToTaskBoard(id, task);
        return "redirect:/taskboards/" + id;
    }
}