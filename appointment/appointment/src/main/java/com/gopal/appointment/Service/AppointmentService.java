package com.gopal.appointment.Service;

import com.gopal.appointment.model.Appointment;
import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpRequest;

public interface AppointmentService {
    public boolean validateAppointment(HttpServletRequest req, Appointment appointment);
    public Appointment addAppointment(Appointment appointment);
}
