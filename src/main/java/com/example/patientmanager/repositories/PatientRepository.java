package com.example.patientmanager.repositories;

import com.example.patientmanager.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Możesz dodać własne metody, np. wyszukiwanie po PESEL
    Patient findByPesel(String pesel);
    List<Patient> findByFirstNameIgnoreCaseStartingWithOrLastNameIgnoreCaseStartingWith(String firstName, String lastName);

}
