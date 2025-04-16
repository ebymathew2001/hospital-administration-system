package com.example.Hospital.Administration.System.service;


import com.example.Hospital.Administration.System.entity.Patient;
import com.example.Hospital.Administration.System.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {


    @Autowired
    private PatientRepository patientRepository;

    public Optional<Patient> findByPatientId(String patientId) {
        return patientRepository.findByPatientId(patientId);
    }

}
