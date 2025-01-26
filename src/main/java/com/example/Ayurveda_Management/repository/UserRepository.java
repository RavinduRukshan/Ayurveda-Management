package com.example.Ayurveda_Management.repository;

import com.example.Ayurveda_Management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndStatus(String username, String status); // To check if a user is inactive

    Optional<User> findByIdAndStatus(int id, String status); // To check if a user is inactive by ID

    // This method can be used to fetch the user by username and check their status
    Optional<User> findByUsernameAndStatusNot(String username, String status);

}
