package com.example.Ayurveda_Management.repository;

import com.example.Ayurveda_Management.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    List<Staff> findByName(String name);
}
