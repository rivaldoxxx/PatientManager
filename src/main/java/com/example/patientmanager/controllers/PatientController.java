package com.example.patientmanager.controllers;

import com.example.patientmanager.entities.Patient;
import com.example.patientmanager.services.PatientService;
import com.example.patientmanager.services.VisitService;
import com.example.patientmanager.entities.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitService visitService;

    // Metody do obsługi widoków HTML

    @GetMapping
    public String showAllPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patients";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Patient> listPatients(@RequestParam(required = false) String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            // Używamy metody wyszukiwania ignorującej wielkość liter
            return patientService.searchPatients(keyword);
        } else {
            // Zwraca pełną listę pacjentów, gdy pole wyszukiwania jest puste
            return patientService.getAllPatients();
        }
    }

    @GetMapping("/add")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "add-patient";  // zwraca widok add-patient.html
    }

    @PostMapping
    public String addPatient(@ModelAttribute Patient patient) {
        patientService.addPatient(patient);
        return "redirect:/patients";  // przekierowuje po zapisaniu
    }
    // Metody do obsługi API REST
    @GetMapping("/api")
    @ResponseBody
    public List<Patient> getAllPatientsApi() {
        return patientService.getAllPatients();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Optional<Patient> getPatientByIdApi(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @PostMapping("/api")
    @ResponseBody
    public Patient addPatientApi(@RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void deletePatientApi(@PathVariable Long id) {
        patientService.deletePatient(id);
    }


    @GetMapping("/{id}")
    public String showPatientDetails(@PathVariable Long id, Model model) {
        Optional<Patient> patient = patientService.getPatientById(id);
        if (patient.isPresent()) {
            model.addAttribute("patient", patient.get());

            // Pobierz ostatnie wizyty pacjenta
            List<Visit> visits = visitService.getLastVisitsForPatient(id);
            model.addAttribute("visits", visits);

            return "patient-details";  // Zwraca widok patient-details.html
        } else {
            return "redirect:/patients";  // Przekierowanie do listy pacjentów, jeśli pacjent nie istnieje
        }
    }

}
