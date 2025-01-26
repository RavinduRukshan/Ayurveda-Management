package com.example.Ayurveda_Management.service;

import com.example.Ayurveda_Management.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {

    List<Patient> findAll();

    Patient findById(int theId);

    void save(Patient thePatient);

    void deleteById(int theId);

    List<Patient> findByNameContainingOrContactNumber(String name, String contactNumber);

}
