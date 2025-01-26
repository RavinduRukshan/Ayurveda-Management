package com.example.Ayurveda_Management.service;

import com.example.Ayurveda_Management.model.Staff;
import com.example.Ayurveda_Management.model.TreatmentRecord;
import com.example.Ayurveda_Management.repository.TreatmentRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TreatmentRecordServiceImpl implements TreatmentRecordService{

    private TreatmentRecordRepository treatmentRecordRepository;

    public TreatmentRecordServiceImpl(TreatmentRecordRepository theTreatmentRecordRepository) {
        treatmentRecordRepository = theTreatmentRecordRepository;
    }

    @Override
    public List<TreatmentRecord> findAll() {
        return treatmentRecordRepository.findAll();
    }

    @Override
    public TreatmentRecord findById(int theId) {
        Optional<TreatmentRecord> result = treatmentRecordRepository.findById(theId);
        TreatmentRecord theRecord = null;

        if (result.isPresent()) {
            theRecord = result.get();
        } else {
            throw new RuntimeException("Did not find the Treatment Record - " + theId);
        }
        return theRecord;
    }

    @Override
    public void save(TreatmentRecord theRecord) {
        treatmentRecordRepository.save(theRecord);
    }

    @Override
    public void deleteById(int theId) {
        treatmentRecordRepository.deleteById(theId);
    }

    @Override
    public List<TreatmentRecord> getTreatmentsByDate(LocalDate date) {
        return treatmentRecordRepository.findByTreatmentDate(date);
    }
}
