package com.example.Ayurveda_Management.service;

import com.example.Ayurveda_Management.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    List<Role> findAll();

    Role findById(int theId);

    void save(Role theRole);

    void deleteById(int theId);

}
