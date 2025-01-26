package com.example.Ayurveda_Management.service;

import com.example.Ayurveda_Management.model.Dispensary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DispensaryService {

    List<Dispensary> findAll();

    Dispensary findById(int theId);

    void save(Dispensary theDispensary);

    void deleteById(int theId);

}
