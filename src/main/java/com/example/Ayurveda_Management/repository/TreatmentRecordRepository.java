package com.example.Ayurveda_Management.repository;

import com.example.Ayurveda_Management.model.TreatmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface TreatmentRecordRepository extends JpaRepository<TreatmentRecord, Integer> {

    List<TreatmentRecord> findByTreatmentDate(LocalDate date);

    List<TreatmentRecord> findByPatientId(int patientId);

}
