package com.example.patientmanager.controllers;

import com.example.patientmanager.entities.Doctor;
import com.example.patientmanager.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // Metody do obsługi widoków HTML

    @GetMapping
    public String showAllDoctors(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "doctors";  // zwraca widok doctors.html
    }

    @GetMapping("/add")
    public String showAddDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "add-doctor";  // zwraca widok add-doctor.html
    }

    @PostMapping
    public String addDoctor(@ModelAttribute Doctor doctor) {
        doctorService.addDoctor(doctor);
        return "redirect:/doctors";  // przekierowuje po zapisaniu
    }

    // Metody do obsługi API REST

    @GetMapping("/api")
    @ResponseBody
    public List<Doctor> getAllDoctorsApi() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Optional<Doctor> getDoctorByIdApi(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @PostMapping("/api")
    @ResponseBody
    public Doctor addDoctorApi(@RequestBody Doctor doctor) {
        return doctorService.addDoctor(doctor);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void deleteDoctorApi(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }
}
