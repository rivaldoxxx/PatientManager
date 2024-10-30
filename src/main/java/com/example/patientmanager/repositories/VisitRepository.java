package com.example.patientmanager.repositories;

import com.example.patientmanager.entities.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query("SELECT v FROM Visit v WHERE v.patient.firstName LIKE %:keyword% OR v.patient.lastName LIKE %:keyword% OR v.doctor.lastName LIKE %:keyword%")
    List<Visit> searchByKeyword(String keyword);

    List<Visit> findByPatientIdOrderByVisitDateDesc(Long patientId);

}
