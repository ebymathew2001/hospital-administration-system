package com.example.Hospital.Administration.System.contoller;


import com.example.Hospital.Administration.System.entity.*;
import com.example.Hospital.Administration.System.repository.AppointmentRepository;
import com.example.Hospital.Administration.System.repository.PatientRepository;
import com.example.Hospital.Administration.System.service.ReceptionService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Controller for handling reception related operations
 */
@Controller
@RequestMapping("/reception")
public class ReceptionController {

    @Autowired
    private ReceptionService receptionService;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;


    /**
     * This method returns the view name of the receptionHome
     * @return the reception home page
     */
    @GetMapping("/reception-home")
    public String showReceptionHome(){
        return "reception/reception-home";
    }

    @GetMapping("/patient-registration")
    public String showPatientRegistrationForm(Model model){
        model.addAttribute("patient",new Patient());
        return "reception/patient-registration";
    }

    @PostMapping("/patient-registration")
    public String registerPatient(@ModelAttribute("patient") Patient patient, Model model) {
        receptionService.registerPatient(patient);
        model.addAttribute("message", "Patient registered successfully!");
        return "redirect:/reception/appointment-booking?patientId=" + patient.getPatientId();  // Redirect to appointment page
    }

    @GetMapping("/appointment-booking")
    public String showAppointmentForm(@RequestParam("patientId") String patientId, Model model) {
        Appointment appointment = receptionService.prepareAppointmentForm(patientId);
        model.addAttribute("appointment", appointment);
        model.addAttribute("departments", receptionService.getAllDepartments());
        model.addAttribute("doctors", receptionService.getAllDoctors());
        return "reception/appointment-form";
    }

    @PostMapping("/appointment-booking")
    public String saveAppointment(@ModelAttribute Appointment appointment, Model model) {
        LocalDate date = appointment.getDate();
        User doctor = appointment.getDoctor();

        // 1. Calculate next available time slot
        LocalTime defaultStartTime = LocalTime.of(9, 0);
        int slotDuration = 15;

        Optional<Appointment> lastAppointment = appointmentRepository
                .findTopByDoctorAndDateOrderByTimeDesc(doctor, date);

        LocalTime nextTime = lastAppointment.map(a -> a.getTime().plusMinutes(slotDuration))
                .orElse(defaultStartTime);
        appointment.setTime(nextTime);

        // 2. Generate token number
        int token = appointmentRepository.countByDoctorAndDate(doctor, date) + 1;
        appointment.setTokenNumber(String.valueOf(token));

        String patientCode = appointment.getPatient().getPatientId();
        Patient existingPatient = patientRepository.findByPatientId(patientCode)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        appointment.setPatient(existingPatient);
        appointment.setStatus(Status.PENDING);

        receptionService.saveAppointment(appointment);
        model.addAttribute("message", "Appointment booked successfully!");
        return "redirect:/reception/reception-home";
    }

    @GetMapping("/appointments")
    public String viewAllAppointmentsForReceptionist(Model model) {
        List<Appointment> appointments = appointmentRepository.findAll(); // optionally add sorting/filtering
        model.addAttribute("appointments", appointments);
        return "reception/view-appointments";
    }

    @PostMapping("/appointment/cancel")
    public String cancelAppointment(@RequestParam("appointmentId") Long appointmentId) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            appointment.setStatus(Status.CANCELLED);
            appointmentRepository.save(appointment);
        }
        return "redirect:/reception/appointments";
    }











}
