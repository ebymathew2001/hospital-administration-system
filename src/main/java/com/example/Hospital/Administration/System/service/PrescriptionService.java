package com.example.Hospital.Administration.System.service;

import com.example.Hospital.Administration.System.entity.Prescription;
import com.example.Hospital.Administration.System.entity.PrescriptionStatus;
import com.example.Hospital.Administration.System.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public List<Prescription> getPendingPrescriptions() {
        return prescriptionRepository.findByStatusOrderByCreatedAtDesc(PrescriptionStatus.PENDING);
    }
}
