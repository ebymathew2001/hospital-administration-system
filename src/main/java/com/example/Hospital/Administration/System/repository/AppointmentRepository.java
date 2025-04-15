package com.example.Hospital.Administration.System.repository;

import com.example.Hospital.Administration.System.entity.Appointment;
import com.example.Hospital.Administration.System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Optional<Appointment> findTopByDoctorAndDateOrderByTimeDesc(User doctor, LocalDate date);

    int countByDoctorAndDate(User doctor, LocalDate date);

    List<Appointment> findByDoctor(User doctor);
}
