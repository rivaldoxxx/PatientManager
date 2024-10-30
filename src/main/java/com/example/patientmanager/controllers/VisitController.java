package com.example.patientmanager.controllers;


import com.example.patientmanager.entities.Visit;
import com.example.patientmanager.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/visits")
public class VisitController {

    @Autowired
    private VisitService visitService;

    @GetMapping
    public String getAllVisits(Model model) {
        List<Visit> visits = visitService.getAllVisits();
        model.addAttribute("visits", visits);
        return "visits"; // Zwraca nazwę widoku "visits.html"
    }

    @PutMapping("/{id}")
    public Visit updateVisit(@PathVariable Long id, @RequestBody Visit updatedVisit) {
        return visitService.updateVisit(id, updatedVisit);
    }

    @GetMapping("/search")
    public String searchVisits(@RequestParam String keyword, Model model) {
        List<Visit> visits = visitService.searchVisits(keyword);
        model.addAttribute("visits", visits);
        return "visits"; // Zwraca widok z wynikami wyszukiwania
    }

    @GetMapping("/{id}")
    public String getVisitById(@PathVariable Long id, Model model) {
        Optional<Visit> visit = visitService.getVisitById(id);
        if (visit.isPresent()) {
            model.addAttribute("visit", visit.get());
            return "visit-details";
        } else {
            return "visit-not-found";
        }
    }


    @PostMapping
    public Visit addVisit(@RequestBody Visit visit) {
        return visitService.addVisit(visit);
    }

    @DeleteMapping("/{id}")
    public void deleteVisit(@PathVariable Long id) {
        visitService.deleteVisit(id);
    }
}
