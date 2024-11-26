package com.danielnak.task_manager_project.controller;

import com.danielnak.task_manager_project.dto.LoginRequest;
import com.danielnak.task_manager_project.dto.ResponseMessage;
import com.danielnak.task_manager_project.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    // Example: Register a new user
    @PostMapping("/register")
    public ResponseEntity<ResponseMessage> registerUser(@RequestBody User user) {
        // Logic to register the user
        return ResponseEntity.ok(new ResponseMessage("User registered successfully"));
    }

    // Example: Login a user
    @PostMapping("/login")
    public ResponseEntity<ResponseMessage> loginUser(@RequestBody LoginRequest loginRequest) {
        // Logic to authenticate the user
        return ResponseEntity.ok(new ResponseMessage("User logged in successfully"));
    }

    // Example: Get user details
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable Long userId) {
        // Logic to fetch user details
        User user = new User("sampleUser", "sample@example.com", "password");
        return ResponseEntity.ok(user);
    }
}
