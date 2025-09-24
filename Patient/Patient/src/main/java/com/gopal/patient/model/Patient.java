package com.gopal.patient.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "patient_detail")
@Data
public class Patient {

    @Id
    private String id;
    // Basic Info
    private String firstName;
    private String lastName;
    private String gender; // Male / Female / Other
    private String dob;    // Date of Birth (store as String or LocalDate)
    private int age;

    // Contact Info
    private String email;
    private String phone;
    private Address address;
    // Additional Demographics
    private String occupation;
    private String maritalStatus; // Single / Married / Divorced / Widowed
    private String bloodGroup;    // A+, O-, etc.
    private String emergencyContact;

    // Health Info
    private List<String> allergies;
    private List<String> chronicConditions; // e.g., Diabetes, Hypertension
    private String insuranceProvider;
    private String insuranceNumber;

    // Admin Fields
    private String userId; // To link with Users collection (optional)
    private Date createdAt;
    private Date updatedAt;





}
