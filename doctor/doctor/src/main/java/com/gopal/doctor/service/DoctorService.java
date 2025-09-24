package com.gopal.doctor.service;

import com.gopal.doctor.model.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DoctorService {
    public List<Doctor> getAllDoctors();
    public Doctor saveDoctor(Doctor doctor);

}
