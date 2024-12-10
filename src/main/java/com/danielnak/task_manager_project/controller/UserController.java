package com.danielnak.task_manager_project.controller;

import com.danielnak.task_manager_project.dto.LoginRequest;
import com.danielnak.task_manager_project.dto.ResponseMessage;
import com.danielnak.task_manager_project.model.User;
import com.danielnak.task_manager_project.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Example: Get user details
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserDetails(@PathVariable Long userId) {
        return userService.findUserById(userId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
