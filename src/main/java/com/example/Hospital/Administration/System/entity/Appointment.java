package com.example.Hospital.Administration.System.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tokenNumber; // Can be UUID or auto-generated string

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor; // Assuming User is the entity for doctors, receptionists, etc.

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private LocalDate date;

    // Getters and Setters
}

