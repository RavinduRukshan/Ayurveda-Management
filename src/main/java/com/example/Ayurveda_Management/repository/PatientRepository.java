package com.example.Ayurveda_Management.repository;

import com.example.Ayurveda_Management.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    List<Patient> findByName(String name);
    List<Patient> findByContactNumber(String contactNumber);
    List<Patient> findByNameContainingOrContactNumber(String name, String contactNumber);

}
