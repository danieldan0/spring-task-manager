package com.danielnak.task_manager_project.controller;

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
public class DashboardController {

    @Autowired
    private TaskBoardService taskBoardService;

    @Autowired
    private UserRepository userRepository;

    // Show the dashboard with taskboards
    @GetMapping("/home")
    public String showDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        // Get the logged-in user
        User user = userRepository.findByUsername(userDetails.getUsername()).orElse(null);

        if (user != null) {
            List<TaskBoard> taskBoards = taskBoardService.getTaskBoardsByUserId(user.getId());
            model.addAttribute("taskBoards", taskBoards);
        }

        return "dashboard";
    }
}