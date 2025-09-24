package com.gopal.doctor.controller;

import com.gopal.doctor.model.Doctor;
import com.gopal.doctor.service.DoctorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController {

    private final DoctorService doctorService;

    public DocController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/msg")
    public String msg() {
        return "Welcome to Doctor Service";
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/save")
    public Doctor save(HttpServletRequest req, @RequestBody Doctor doctor) {
//        System.out.println("INCOMING: " + doctor.getFirstName() + ", " + doctor.getLastName()
//                + ", " + doctor.getEmail() + ", " + doctor.getPhone() + ", " + doctor.getDesignation());
        return doctorService.saveDoctor(doctor);

    }
    @GetMapping("/doctors")
    public List<Doctor> getDoctors() {
        return doctorService.getAllDoctors();
    }
}
