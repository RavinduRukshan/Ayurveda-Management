package com.example.Ayurveda_Management.controller;

import com.example.Ayurveda_Management.model.User;
import com.example.Ayurveda_Management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    private UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String viewProfile(Authentication authentication, Model model) {
        // Retrieve the username of the currently logged-in user
        final String username; // Declare username as final
        if (authentication.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        } else {
            // If no user is authenticated, redirect to login
            return "redirect:/login";
        }

        // Fetch user details from the database
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found for username: " + username));

        // Add user details to the model
        model.addAttribute("user", user);

        // Return the profile view
        return "profile"; // This should match the Thymeleaf template name
    }

}
