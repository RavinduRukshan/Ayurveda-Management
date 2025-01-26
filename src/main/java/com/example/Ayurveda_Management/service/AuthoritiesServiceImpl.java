package com.example.Ayurveda_Management.service;

import com.example.Ayurveda_Management.model.Authorities;
import com.example.Ayurveda_Management.repository.AuthoritiesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService{

    private AuthoritiesRepository authoritiesRepository;

    public AuthoritiesServiceImpl(AuthoritiesRepository theAuthoritiesRepository) {
        authoritiesRepository = theAuthoritiesRepository;
    }

    @Override
    public List<Authorities> findAll() {
        return authoritiesRepository.findAll();
    }

    @Override
    public Authorities findById(int theId) {
        Optional<Authorities> result = authoritiesRepository.findById(theId);
        Authorities theAuthorities = null;

        if (result.isPresent()) {
            theAuthorities = result.get();
        } else {
            throw new RuntimeException("Didn't find the authority - " + theId);
        }

        return theAuthorities;
    }

    @Override
    public void save(Authorities theAuthorities) {
        authoritiesRepository.save(theAuthorities);
    }

    @Override
    public void deleteById(int theId) {
        authoritiesRepository.deleteById(theId);
    }

}
