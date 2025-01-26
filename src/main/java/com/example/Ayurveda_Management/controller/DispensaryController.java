package com.example.Ayurveda_Management.controller;

import com.example.Ayurveda_Management.model.Dispensary;
import com.example.Ayurveda_Management.model.Patient;
import com.example.Ayurveda_Management.service.DispensaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dispensary")
public class DispensaryController {

    private DispensaryService dispensaryService;

    public DispensaryController(DispensaryService theDispensaryService) {
        dispensaryService = theDispensaryService;
    }

    @GetMapping("/list")
    public String listDispensaries(Model theModel) {
        List<Dispensary> theDispensaries = dispensaryService.findAll();
        theModel.addAttribute("dispensaries", theDispensaries);
        return "dispensary-list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("patientId") Integer theId, Model theModel) {
        Dispensary theDispensary = dispensaryService.findById(theId);
        theModel.addAttribute("dispensary", theDispensary);
        return "dispensary-form";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute("dispensary") Dispensary theDispensary) {
        dispensaryService.save(theDispensary);
        return "redirect:/dispensary/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("dispensaryId") Integer theId) {
        dispensaryService.deleteById(theId);
        return "redirect:/dispensary/list";
    }

}
