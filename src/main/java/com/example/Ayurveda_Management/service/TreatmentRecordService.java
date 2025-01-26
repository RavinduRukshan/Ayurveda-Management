package com.example.Ayurveda_Management.service;

import com.example.Ayurveda_Management.model.TreatmentRecord;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface TreatmentRecordService {

    List<TreatmentRecord> findAll();

    TreatmentRecord findById(int theId);

    void save(TreatmentRecord theRecord);

    void deleteById(int theId);

    List<TreatmentRecord> getTreatmentsByDate(LocalDate date);

}
