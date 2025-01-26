package com.example.Ayurveda_Management.service;

import com.example.Ayurveda_Management.model.Role;
import com.example.Ayurveda_Management.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository theRoleRepository) {
        roleRepository = theRoleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(int theId) {
        Optional<Role> result = roleRepository.findById(theId);
        Role theRole = null;

        if (result.isPresent()) {
            theRole = result.get();
        } else {
            throw new RuntimeException("Didn't find the Role - " + theId);
        }

        return theRole;
    }

    @Override
    public void save(Role theRole) {
        roleRepository.save(theRole);
    }

    @Override
    public void deleteById(int theId) {
        roleRepository.deleteById(theId);
    }
}
