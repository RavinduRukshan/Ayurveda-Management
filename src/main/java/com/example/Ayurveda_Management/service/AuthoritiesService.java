package com.example.Ayurveda_Management.service;

import com.example.Ayurveda_Management.model.Authorities;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthoritiesService {

    List<Authorities> findAll();

    Authorities findById(int theId);

    void save(Authorities theAuthorities);

    void deleteById(int theId);

}
