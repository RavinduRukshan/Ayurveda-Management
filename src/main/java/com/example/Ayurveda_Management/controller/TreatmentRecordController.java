package com.example.Ayurveda_Management.controller;

import com.example.Ayurveda_Management.model.*;
import com.example.Ayurveda_Management.service.PatientService;
import com.example.Ayurveda_Management.service.StaffService;
import com.example.Ayurveda_Management.service.TreatmentRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/treatment")
public class TreatmentRecordController {

    private TreatmentRecordService treatmentRecordService;
    private final PatientService patientService;
    private final StaffService staffService;

    public TreatmentRecordController(PatientService patientService, StaffService staffService, TreatmentRecordService treatmentRecordService) {
        this.patientService = patientService;
        this.staffService = staffService;
        this.treatmentRecordService = treatmentRecordService;
    }

    @GetMapping("/list")
    public String listRecord(@RequestParam(value = "date", required = false) String date, Model theModel) {
        List<TreatmentRecord> theRecords;

        if (date == null || date.isBlank()) {
            // Default to today's records
            theRecords = treatmentRecordService.getTreatmentsByDate(LocalDate.now());
        } else {
            // Parse the provided date
            LocalDate treatmentDate = LocalDate.parse(date);
            theRecords = treatmentRecordService.getTreatmentsByDate(treatmentDate);
        }

        theModel.addAttribute("records", theRecords);
        theModel.addAttribute("selectedDate", date != null ? date : LocalDate.now().toString());
        return "record-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        TreatmentRecord theRecord = new TreatmentRecord();
        theModel.addAttribute("record", theRecord);

        // Add Patient to the model
        List<Patient> patients = patientService.findAll();
        theModel.addAttribute("patient", patients);

        // Add staff to the model
        List<Staff> staff = staffService.findAll();
        theModel.addAttribute("staff", staff);

        return "record-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("staffId") Integer theId, Model theModel) {
        Staff theStaff = staffService.findById(theId);
        theModel.addAttribute("staff", theStaff);

        // Add Patient to the model
        List<Patient> patients = patientService.findAll();
        theModel.addAttribute("patient", patients);

        // Add staff to the model
        List<Staff> staff = staffService.findAll();
        theModel.addAttribute("staff", staff);

        return "record-form";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute("record") TreatmentRecord record) {
        treatmentRecordService.save(record);
        return "redirect:/treatment/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("recordId") Integer theId) {
        treatmentRecordService.deleteById(theId);
        return "redirect:/treatment/list";
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

    // Search patients by contact number
//    public List<Patient> searchPatientByNameOrContact(@RequestParam("query") String name, String contactNumber)) {
//        if (query == null || query.isBlank()) {
//            return Collections.emptyList();
//        }
//        return patientService.findByNameContainingOrContactNumber(query, query);
//    }

    // Search staff by name
    @GetMapping("/staff/searchByName")
    @ResponseBody
    public List<Staff> searchStaffByName(@RequestParam("name") String name) {
        if (name == null || name.isBlank()) {
            return Collections.emptyList();
        }
        return staffService.findByName(name);
    }

    // Filter Treatments by Date (HTML Response)
    @GetMapping("/filter")
    public String filterTreatmentsByDate(@RequestParam("date") String date, Model theModel) {
        LocalDate treatmentDate = LocalDate.parse(date);
        List<TreatmentRecord> filteredRecords = treatmentRecordService.getTreatmentsByDate(treatmentDate);
        theModel.addAttribute("records", filteredRecords);
        return "record-list";
    }

}
