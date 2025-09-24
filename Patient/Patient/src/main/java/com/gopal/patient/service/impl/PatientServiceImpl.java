package com.gopal.patient.service.impl;

import com.gopal.patient.model.Patient;
import com.gopal.patient.repository.PatientRepository;
import com.gopal.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

   @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> getPatientById(int id) {
        return patientRepository.findById(id);
    }

    @Override
    public Patient addPatient(Patient patient,String userId) {
        patient.setUserId(userId);
        return patientRepository.save(patient);

    }
}
