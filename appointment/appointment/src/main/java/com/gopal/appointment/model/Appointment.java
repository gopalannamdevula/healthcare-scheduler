package com.gopal.appointment.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="Appointment")
@Data
public class Appointment {

    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Long id;
    private String patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String reason;
    private String date;
    private String time;
    private String status;



}
