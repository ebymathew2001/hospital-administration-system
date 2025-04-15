package com.example.Hospital.Administration.System.service;

import com.example.Hospital.Administration.System.contoller.ReceptionController;
import com.example.Hospital.Administration.System.entity.*;
import com.example.Hospital.Administration.System.repository.AppointmentRepository;
import com.example.Hospital.Administration.System.repository.DepartmentRepository;
import com.example.Hospital.Administration.System.repository.PatientRepository;
import com.example.Hospital.Administration.System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReceptionService {


    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    public void registerPatient(Patient patient) {
        String generatedId = "PID-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        patient.setPatientId(generatedId);

        patientRepository.save(patient);

        String subject = "Your Hospital Patient ID";
        String body = "Dear " + patient.getName() + ",\n\nYour Patient ID is: " + generatedId +
                "\nPlease keep this ID safe for booking appointments.\n\nRegards,\nHospital Team";

         emailService.sendEmail(patient.getEmail(), subject, body);
    }

    public Appointment prepareAppointmentForm(String patientId) {
        Patient patient = patientRepository.findByPatientId(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + patientId));
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        return appointment;
    }

    public void saveAppointment(Appointment appointment) {

        appointmentRepository.save(appointment);
    }

    public List<User> getAllDoctors() {
       return userRepository.findByRole(Role.DOCTOR); // ✅ Correct usage

    }


    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

}
