package com.example.patientmanager.services;



import com.example.patientmanager.entities.Visit;
import com.example.patientmanager.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitService {

    @Autowired
    private VisitRepository visitRepository;

    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    public Optional<Visit> getVisitById(Long id) {
        return visitRepository.findById(id);
    }

    public Visit addVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    public void deleteVisit(Long id) {
        visitRepository.deleteById(id);
    }

    public List<Visit> searchVisits(String keyword) {
        return visitRepository.searchByKeyword(keyword);
    }

    public List<Visit> getLastVisitsForPatient(Long patientId) {
        return visitRepository.findByPatientIdOrderByVisitDateDesc(patientId);
    }
    public Visit updateVisit(Long id, Visit updatedVisit) {
        Optional<Visit> existingVisit = visitRepository.findById(id);
        if (existingVisit.isPresent()) {
            Visit visit = existingVisit.get();
            visit.setVisitDate(updatedVisit.getVisitDate());
            visit.setVisitReason(updatedVisit.getVisitReason());
            visit.setNotes(updatedVisit.getNotes());
            visit.setDoctor(updatedVisit.getDoctor());
            visit.setPatient(updatedVisit.getPatient());
            return visitRepository.save(visit);
        }
        return null; // lub można rzucić wyjątek np. VisitNotFoundException
    }
}
