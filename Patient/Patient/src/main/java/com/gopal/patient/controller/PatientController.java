package com.gopal.patient.controller;


import com.gopal.patient.config.JwtUtil;
import com.gopal.patient.model.Patient;
import com.gopal.patient.repository.PatientRepository;
import com.gopal.patient.service.PatientService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PatientController {

    private final PatientService patientService;
    private final JwtUtil jwtUtil;

    @Autowired
    public PatientController(PatientService patientService, JwtUtil jwtUtil) {
        this.patientService = patientService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/message")
    public String getMessage(){
        System.out.println(" Entered /meassage controller");
        return "Welcome to the Patient Controller";
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @PostMapping("/msg")
    public String getMsg(@RequestBody String msg){
        return "this is the "+msg;
    }

    @PostMapping("/save")
    public Patient addPatient(HttpServletRequest req, @RequestBody Patient patient){
        System.out.println(" Entered /save controller");
        String header = req.getHeader("Authorization");
        String token = null;
        String userId = null;

        // Extract Bearer token
        if(header != null && header.startsWith("Bearer ")){
            token = header.substring(7);
            userId  = jwtUtil.extractUserIdFromToken(token);
        }
      return patientService.addPatient(patient,userId);
    }


}
