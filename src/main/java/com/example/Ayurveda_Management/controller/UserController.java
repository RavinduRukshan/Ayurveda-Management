package com.example.Ayurveda_Management.controller;

import com.example.Ayurveda_Management.model.Authorities;
import com.example.Ayurveda_Management.model.Staff;
import com.example.Ayurveda_Management.model.User;
import com.example.Ayurveda_Management.service.AuthoritiesService;
import com.example.Ayurveda_Management.service.StaffService;
import com.example.Ayurveda_Management.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private AuthoritiesService authoritiesService;
    private StaffService staffService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${file.upload-dir}") // Directory for file uploads (configured in application.properties)
    private String uploadDir;

    @Autowired
    public UserController(UserService userService, AuthoritiesService authoritiesService, StaffService staffService) {
        this.userService = userService;
        this.authoritiesService = authoritiesService;
        this.staffService = staffService;
    }


    // Display all users (Admin only)
    @GetMapping("/list")
    public String listUsers(Model theModel) {
        List<User> theUsers = userService.findAll();
        theModel.addAttribute("users", theUsers);
        return "user-list"; // View for displaying users
    }


    // Show form for adding or updating user (Admin only)
    @GetMapping("/showForm")
    public String showForm(@RequestParam(value = "userId", required = false) Integer theId, Model theModel) {
        User theUser = (theId != null) ? userService.findById(theId) : new User();
        theModel.addAttribute("user", theUser);
        populateModelForForm(theModel); // Populate dropdowns for roles and staff
        return "user-form";
    }


//    // Show form for updating user (Admin only)
//    @GetMapping("/showFormForUpdate")
//    public String showFormForUpdate(@RequestParam("userId") int theId, Model theModel) {
//        User theUser = userService.findById(theId);
//        if (theUser == null) {
//            theModel.addAttribute("error", "User not found.");
//            return "redirect:/admin/list";
//        }
//        theModel.addAttribute("user", theUser);
//        populateModelForForm(theModel); // Populate dropdowns for roles and staff
//        return "user-form";
//    }


    // Save or update user (Admin only)
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User theUser,
                           @RequestParam(value = "profileImage", required = false) MultipartFile file,
                           Model theModel) {

        // Handle file upload
        try {
            if (file != null && !file.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                File saveFile = new File(uploadDir + fileName);
                saveFile.getParentFile().mkdirs(); // Ensure directories exist
                file.transferTo(saveFile); // Save the file
                theUser.setProfileImagePath(uploadDir + fileName);
            }
        } catch (IOException e) {
            logger.error("File upload failed: {}", e.getMessage(), e);
            theModel.addAttribute("error", "File upload failed.");
            populateModelForForm(theModel);
            return "user-form";
        }

        // Prevent duplicate usernames on save
        if (theUser.getId() == 0 && userService.isUsernameExists(theUser.getUsername())) {
            theModel.addAttribute("error", "The username already exists. Please choose another one.");
            populateModelForForm(theModel);
            return "user-form";
        }

        // Validate and set role
        Authorities role = authoritiesService.findById(theUser.getRole().getId());
        if (role == null) {
            theModel.addAttribute("error", "Invalid role selected.");
            populateModelForForm(theModel);
            return "user-form";
        }
        theUser.setRole(role);

        // Validate and set staff
        Staff staff = staffService.findById(theUser.getStaff().getId());
        if (staff == null) {
            theModel.addAttribute("error", "Invalid staff selected.");
            populateModelForForm(theModel);
            return "user-form";
        }
        theUser.setStaff(staff);

        // Save the user
        userService.save(theUser);
        logger.info("User saved successfully: {}", theUser.getUsername());
        return "redirect:/user/list";
    }


    // Delete a user (Admin only)
    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") int theId) {
        try {
            userService.deleteById(theId);
            logger.info("User deleted successfully with ID: {}", theId);
        } catch (RuntimeException e) {
            logger.error("Error deleting user with ID {}: {}", theId, e.getMessage());
        }
        return "redirect:/user/list";
    }


    // Real-time check for existing username (AJAX request)
    @GetMapping("/check-username")
    @ResponseBody
    public boolean checkUsername(@RequestParam("username") String username) {
//        return !userService.isUsernameExists(username);
        boolean exists = userService.isUsernameExists(username);
        System.out.println("Username check for: " + username + ", exists: " + exists);
        return exists;
    }


    // Search staff by name (for AJAX functionality)
    @GetMapping("/staff/search")
    @ResponseBody
    public List<Staff> searchStaff(@RequestParam("query") String query) {
        if (query == null || query.isBlank()) {
            return Collections.emptyList();
        }
        return staffService.findByName(query);
    }

    // Update last_login time when user logs in
    @PostMapping("/update-last-login")
    public ResponseEntity<Void> updateLastLogin() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOpt = userService.findByUsername(username);
        userOpt.ifPresent(userService::updateLastLogin);
        return ResponseEntity.ok().build();
    }

    // Utility method to populate model attributes for form dropdowns
    private void populateModelForForm(Model theModel) {
        List<Authorities> authorities = authoritiesService.findAll();
        List<Staff> staffList = staffService.findAll();
        theModel.addAttribute("authorities", authorities); // Populate roles
        theModel.addAttribute("staffList", staffList); // Populate staff members
    }

}
