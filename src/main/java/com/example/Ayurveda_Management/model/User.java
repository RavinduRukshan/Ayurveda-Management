package com.example.Ayurveda_Management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "useraccount")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    @NotBlank(message = "Username is required")
    @Size(min = 4, message = "Username must have at least 4 characters")
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank(message = "Password is required")
    @Column(name = "password")
    private String password;

    @Column(name = "profile_image_path")
    private String profileImagePath;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Authorities role;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;



    // constructor
    public User() {

    }

    public User(Staff staff, String username, String password, String profileImagePath, Authorities role,
                LocalDateTime lastLogin, Status status) {
        this.staff = staff;
        this.username = username;
        this.password = password;
        this.profileImagePath = profileImagePath;
        this.role = role;
        this.lastLogin = lastLogin;
        this.status = status;
    }



    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public Authorities getRole() {
        return role;
    }

    public void setRole(Authorities role) {
        this.role = role;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



    // toString
    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", staff=" + staff +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", profileImagePath='" + profileImagePath + '\'' +
                ", role=" + role +
                ", lastLogin=" + lastLogin +
                ", status=" + status +
                '}';
    }

}
