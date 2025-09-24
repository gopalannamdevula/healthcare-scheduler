package com.gopal.appointment.Service.Impl;

import com.gopal.appointment.Feign.PatientClient;
import com.gopal.appointment.Service.AppointmentService;
import com.gopal.appointment.config.JWTUtil;
import com.gopal.appointment.model.Appointment;
import com.gopal.appointment.repository.AppointmentRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;

@Service
public class AppointmentImpl implements AppointmentService {

    private final PatientClient patientClient;

    private final JWTUtil jwtUtil;
    private final AppointmentRepository appointmentRepository;

    public AppointmentImpl(PatientClient patientClient, JWTUtil jwtUtil, AppointmentRepository appointmentRepository) {
        this.patientClient = patientClient;
        this.jwtUtil = jwtUtil;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public boolean validateAppointment(HttpServletRequest req, Appointment appointment) {
         String header = req.getHeader("Authorization");
         String userId = null;
         if (header != null && header.startsWith("Bearer ")) {
             String token = header.substring(7);
             userId = jwtUtil.getUserId(token);
         }
        return patientClient.verifyUser(userId);
    }

    @Override
    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
}
