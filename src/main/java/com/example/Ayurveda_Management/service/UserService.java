package com.example.Ayurveda_Management.service;

import com.example.Ayurveda_Management.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> findAll();

    User findById(int theId);

    void save(User theUser);

    void deleteById(int theId);

    Optional<User> findByUsername(String username);

    boolean isUsernameExists(String username);

    void updateLastLogin(User user); // To update the last login timestamp

    void logoutUser(User user); // To log out the user when inactive or deleted
}

