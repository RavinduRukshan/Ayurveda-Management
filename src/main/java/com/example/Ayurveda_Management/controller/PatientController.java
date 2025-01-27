package com.example.Ayurveda_Management.controller;

import com.example.Ayurveda_Management.model.Patient;
import com.example.Ayurveda_Management.model.User;
import com.example.Ayurveda_Management.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @GetMapping("/list")
    public String showPatientList(Model model) {
        List<Patient> thePatients = patientService.findAll();  // Get all patients from the database
        model.addAttribute("patients", thePatients);  // Pass the patients to the view

        model.addAttribute("patient", new Patient());  // Initialize a new Patient object for the form
        return "patient-list";  // Thymeleaf page
    }


//     Show form to update existing patient
    @GetMapping("/showFormForUpdate")
    @ResponseBody
    public String showFormForUpdate(@RequestParam("patientId") int theId, Model theModel) {
        Patient thePatient = patientService.findById(theId);
        theModel.addAttribute("patient", thePatient);
        return "patient-list";  // Return to the patient-list page
    }


    // Save patient (either add or update)

    @PostMapping("/save")
    public String savePatient(@ModelAttribute("patient") Patient thePatient) {
        patientService.save(thePatient);
        return "redirect:/patient/list";  // Redirect to the patient list
    }

    // Delete patient
    @GetMapping("/delete")
    public String delete(@RequestParam("patientId") int theId) {
        patientService.deleteById(theId);
        return "redirect:/patient/list";  // Redirect to the patient list
    }
}
