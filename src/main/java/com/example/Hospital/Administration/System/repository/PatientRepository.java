package com.example.Hospital.Administration.System.repository;

import com.example.Hospital.Administration.System.entity.Patient;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    Optional<Patient> findByPatientId(String patientId);

}
