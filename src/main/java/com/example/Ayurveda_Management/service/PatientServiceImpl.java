package com.example.Ayurveda_Management.service;

import com.example.Ayurveda_Management.model.Patient;
import com.example.Ayurveda_Management.model.TreatmentRecord;
import com.example.Ayurveda_Management.repository.PatientRepository;
import com.example.Ayurveda_Management.repository.TreatmentRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService{

    private PatientRepository patientRepository;
    private TreatmentRecordRepository treatmentRecordRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository thePatientRepository, TreatmentRecordRepository theTreatmentRecordRepository) {
        patientRepository = thePatientRepository;
        treatmentRecordRepository = theTreatmentRecordRepository;
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findById(int theId) {
        Optional<Patient> result = patientRepository.findById(theId);

        Patient thePatient = null;

        if (result.isPresent()) {
            thePatient = result.get();
        } else {
            throw new RuntimeException("Did not find Patient id " + theId);
        }
        return thePatient;
    }

    @Override
    public void save(Patient thePatient) {
        patientRepository.save(thePatient);
    }

    @Override
    public void deleteById(int theId) {
        patientRepository.deleteById(theId);
    }

    @Override
    public List<Patient> searchPatients(String query) {
        return patientRepository.findByNameContainingIgnoreCaseOrContactNumberContainingIgnoreCase(query, query);
    }

//    @Override
//    public List<Patient> findByNameContainingOrContactNumber(String name, String contactNumber) {
//        return patientRepository.findByNameContainingOrContactNumber(name, contactNumber);
//    }

    @Override
    public List<TreatmentRecord> findTreatmentRecordsByPatientId(int patientId) {
        return treatmentRecordRepository.findByPatientId(patientId);
    }

//    @Override
//    public List<Patient> findByName(String name) {
//        return patientRepository.findByName(name);
//    }
//
//    @Override
//    public List<Patient> findByContactNumber(String contactNumber) {
//        return patientRepository.findByContactNumber(contactNumber);
//    }

}
