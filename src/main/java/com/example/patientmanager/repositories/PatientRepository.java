package com.example.patientmanager.repository;

import com.example.patientmanager.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Możesz dodać własne metody, np. wyszukiwanie po PESEL
    Patient findByPesel(String pesel);
}
