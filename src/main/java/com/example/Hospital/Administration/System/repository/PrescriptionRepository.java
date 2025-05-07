package com.example.Hospital.Administration.System.repository;

import com.example.Hospital.Administration.System.entity.Prescription;
import com.example.Hospital.Administration.System.entity.PrescriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {

    List<Prescription> findByStatusOrderByCreatedAtDesc(PrescriptionStatus status);
}
