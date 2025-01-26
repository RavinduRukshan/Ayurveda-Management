package com.example.Ayurveda_Management.service;

import com.example.Ayurveda_Management.model.User;
import com.example.Ayurveda_Management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int theId) {
        return userRepository.findById(theId)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + theId));
    }

    @Override
    public void save(User theUser) {
        // Hash the password before saving if not already hashed
        if (theUser.getPassword() != null && !theUser.getPassword().startsWith("$2a$")) { // bcrypt hashed passwords start with $2a$
            theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
        }
        userRepository.save(theUser);
    }

    @Override
    public void deleteById(int theId) {
        Optional<User> user = userRepository.findById(theId);
        if (user.isPresent()) {
            // Log out the user in real-time if active
            if ("Active".equals(user.get().getStatus())) {
                logoutUser(user.get());
            }
            userRepository.deleteById(theId);
        } else {
            throw new RuntimeException("User not found for deletion with ID: " + theId);
        }
    }

    @Override
    public boolean isUsernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public void updateLastLogin(User user) {
        // Convert current timestamp (long) to LocalDateTime
        LocalDateTime lastLogin = Instant.ofEpochMilli(System.currentTimeMillis())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        user.setLastLogin(lastLogin); // Set the last login time
        save(user); // Save the user to update the last login field
    }

    @Override
    public void logoutUser(User user) {
        // Handle real-time logout logic
        if (user != null) {
            // Clear the current authentication context
            SecurityContextHolder.clearContext();
            // Optionally log this action for auditing
            System.out.println("User " + user.getUsername() + " has been logged out.");
        }

    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
