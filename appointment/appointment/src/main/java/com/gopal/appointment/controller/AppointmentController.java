package com.gopal.appointment.controller;

import com.gopal.appointment.Service.AppointmentService;
import com.gopal.appointment.model.Appointment;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;

@RestController
@RequestMapping("/appoint")
public class AppointmentController {

    private final AppointmentService appointmentService;
    public AppointmentController(AppointmentService appointmentService) {

        this.appointmentService = appointmentService;
    }

    @GetMapping("/msg")
    public String msg() {
        return "welcome to Appointments";
    }

    @PostMapping("/add")
    public Appointment addAppointment(HttpServletRequest req, @RequestBody Appointment appointment) {
        if(appointmentService.validateAppointment(req,appointment)) {
            return appointmentService.addAppointment(appointment);
        }
        return appointment;
    }
}
