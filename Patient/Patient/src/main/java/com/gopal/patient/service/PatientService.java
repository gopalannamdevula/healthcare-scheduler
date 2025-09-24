package com.gopal.patient.service;

import com.gopal.patient.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    List<Patient> getAllPatients();
    Optional<Patient> getPatientById(int id);
    Patient addPatient(Patient patient,String userId);
}
