package com.danielnak.task_manager_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        // Optionally, you can pass dynamic data to the home page (e.g., user's name or role)
        model.addAttribute("message", "Welcome to the Home Page!");

        return "home"; // Render the home.html page from templates
    }
}