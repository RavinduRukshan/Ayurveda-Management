package com.example.Ayurveda_Management.service;

import com.example.Ayurveda_Management.model.Dispensary;
import com.example.Ayurveda_Management.repository.DispensaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DispensaryServiceImpl implements DispensaryService{

    private DispensaryRepository dispensaryRepository;

    @Autowired
    public DispensaryServiceImpl(DispensaryRepository theDispensaryRepository) {
        dispensaryRepository = theDispensaryRepository;
    }

    @Override
    public List<Dispensary> findAll() {
        return dispensaryRepository.findAll();
    }

    @Override
    public Dispensary findById(int theId) {
        Optional<Dispensary> result = dispensaryRepository.findById(theId);

        Dispensary theDispensary = null;

        if (result.isPresent()) {
            theDispensary = result.get();
        } else {
            throw new RuntimeException("Didn't find the dispensary id - " + theId);
        }

        return theDispensary;
    }

    @Override
    public void save(Dispensary theDispensary) {
        dispensaryRepository.save(theDispensary);
    }

    @Override
    public void deleteById(int theId) {
        dispensaryRepository.deleteById(theId);
    }
}
