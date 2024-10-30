package com.example.patientmanager.services;

import com.example.patientmanager.entities.Patient;
import com.example.patientmanager.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }


    public List<Patient> searchPatients(String keyword) {
        return patientRepository.findByFirstNameIgnoreCaseStartingWithOrLastNameIgnoreCaseStartingWith(keyword,keyword);
    }
}
