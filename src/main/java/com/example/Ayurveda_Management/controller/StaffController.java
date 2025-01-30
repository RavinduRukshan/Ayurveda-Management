package com.example.Ayurveda_Management.controller;

import com.example.Ayurveda_Management.model.Dispensary;
import com.example.Ayurveda_Management.model.Patient;
import com.example.Ayurveda_Management.model.Role;
import com.example.Ayurveda_Management.model.Staff;
import com.example.Ayurveda_Management.service.DispensaryService;
import com.example.Ayurveda_Management.service.RoleService;
import com.example.Ayurveda_Management.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {

    private StaffService staffService;
    private RoleService roleService;
    private DispensaryService dispensaryService;

    @Autowired
    public StaffController(StaffService theStaffService, RoleService theRoleService,
                           DispensaryService theDispensaryService) {
        staffService = theStaffService;
        roleService = theRoleService;
        dispensaryService = theDispensaryService;
    }

    @GetMapping("/list")
    public String listStaff(Model theModel) {
        List<Staff> theStaffs = staffService.findAll();
        theModel.addAttribute("staffs", theStaffs);
        return "staff-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Staff theStaff = new Staff();
        theModel.addAttribute("staff", theStaff);

        // Add roles to the model
        List<Role> roles = roleService.findAll();
        theModel.addAttribute("roles", roles);

        // Add dispensaries to the model
        List<Dispensary> dispensaries = dispensaryService.findAll();
        theModel.addAttribute("dispensaries", dispensaries);

        return "staff-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("staffId") Integer theId, Model theModel) {
        Staff theStaff = staffService.findById(theId);
        theModel.addAttribute("staff", theStaff);

        // Add roles to the model
        List<Role> roles = roleService.findAll();
        theModel.addAttribute("roles", roles);

        // Add dispensaries to the model
        List<Dispensary> dispensaries = dispensaryService.findAll();
        theModel.addAttribute("dispensaries", dispensaries);

        return "staff-form";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute("staff") Staff theStaff) {

        // Fetch the full Role and Dispensary objects
        Role role = roleService.findById(theStaff.getRole().getId());
        Dispensary dispensary = dispensaryService.findById(theStaff.getDispensary().getId());

        theStaff.setRole(role);
        theStaff.setDispensary(dispensary);

        staffService.save(theStaff);
        return "redirect:/staff/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("staffId") Integer theId) {
        staffService.deleteById(theId);
        return "redirect:/staff/list";
    }

}
