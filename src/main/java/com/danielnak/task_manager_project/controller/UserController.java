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

    // Example: Register a new user
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
        }
    }

    // Example: Login a user
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            return ResponseEntity.ok(new ResponseMessage(userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword())));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
        }
    }

    // Example: Get user details
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserDetails(@PathVariable Long userId) {
        return userService.findUserById(userId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
