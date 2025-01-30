package com.example.Ayurveda_Management.controller;

import com.example.Ayurveda_Management.model.Patient;
import com.example.Ayurveda_Management.model.TreatmentRecord;
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
    public String getAllPatients(Model theModel) {
        List<Patient> thePatients = patientService.findAll();
        theModel.addAttribute("patients", thePatients);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authenticated user roles: " + authentication.getAuthorities());
        return "patient-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Patient thePatient = new Patient();
        theModel.addAttribute("patient", thePatient);
        return "patient-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("patientId") int theId, Model theModel) {
        Patient thePatient = patientService.findById(theId);
        theModel.addAttribute("patient", thePatient);
        return "patient-form";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute("patient") Patient thePatient) {
        patientService.save(thePatient);
        return "redirect:/patient/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("patientId") int theId) {
        patientService.deleteById(theId);
        return "redirect:/patient/list";
    }

    @GetMapping("/profile")
    public String showPatientProfile(@RequestParam("patientId") int theId, Model theModel) {
        Patient thePatient = patientService.findById(theId);
        List<TreatmentRecord> treatmentRecords = patientService.findTreatmentRecordsByPatientId(theId);

        theModel.addAttribute("patient", thePatient);
        theModel.addAttribute("treatmentRecords", treatmentRecords);

        return "patient-profile";
    }

    // Search for Patients by Name, Contact Number
    @GetMapping("/searchPatient")
    @ResponseBody
    public List<Patient> searchPatients(@RequestParam("query") String query) {
        return patientService.searchPatients(query);
    }

}
