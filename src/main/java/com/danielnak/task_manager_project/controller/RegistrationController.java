package com.danielnak.task_manager_project.controller;

import com.danielnak.task_manager_project.dto.UserDto;
import com.danielnak.task_manager_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto()); // Pass a new user data transfer object to the view
        return "register"; // Render the registration page
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserDto userDto) {
        // Encrypt the password before saving to the database
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Save the user to the database
        userService.saveUser(userDto);

        // Redirect to the login page after successful registration
        return "redirect:/login?registered=true";
    }
}