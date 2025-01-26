package com.example.Ayurveda_Management.service;

import com.example.Ayurveda_Management.model.Staff;
import com.example.Ayurveda_Management.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService{

    private StaffRepository staffRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository theStaffRepository) {
        staffRepository = theStaffRepository;
    }

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public Staff findById(int theId) {
        Optional<Staff> result = staffRepository.findById(theId);
        Staff theStaff = null;

        if (result.isPresent()) {
            theStaff = result.get();
        } else {
            throw new RuntimeException("Did not find the Staff member - " + theId);
        }
        return theStaff;
    }

    @Override
    public void save(Staff theStaff) {
        staffRepository.save(theStaff);
    }

    @Override
    public void deleteById(int theId) {
        staffRepository.deleteById(theId);
    }

    @Override
    public List<Staff> findByName(String name) {
        return staffRepository.findByName(name);
    }


}
