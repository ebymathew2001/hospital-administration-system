package com.example.Hospital.Administration.System.contoller;

import com.example.Hospital.Administration.System.entity.Appointment;
import com.example.Hospital.Administration.System.entity.Prescription;
import com.example.Hospital.Administration.System.entity.Status;
import com.example.Hospital.Administration.System.entity.User;
import com.example.Hospital.Administration.System.repository.AppointmentRepository;
import com.example.Hospital.Administration.System.repository.PatientRepository;
import com.example.Hospital.Administration.System.repository.PrescriptionRepository;
import com.example.Hospital.Administration.System.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctor")
public class DoctorController {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;


    @Autowired
    private PrescriptionRepository prescriptionRepository;




    @GetMapping("/doctor-home")
    public String showDoctorHome(){
        return "doctor/doctor-home";
    }

    @GetMapping("/appointments")
    public String viewAppointments(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/doctor-home";
        }
        Optional<User> doctorOptional = userRepository.findByEmail(principal.getName());

        if (!doctorOptional.isPresent()) {
            model.addAttribute("error", "Doctor not found");
            return "error-page";  // Or whatever error page you have
        }
        User doctor = doctorOptional.get();
        List<Appointment> appointments = appointmentRepository.findByDoctor(doctor);
        model.addAttribute("appointments", appointments);
        return "doctor/view-appointments";
    }


    //method for changing the status
    @PostMapping("/appointment/complete")
    public String markAppointmentAsCompleted(@RequestParam("appointmentId") Long appointmentId) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            appointment.setStatus(Status.COMPLETED);
            appointmentRepository.save(appointment);
        }
        return "redirect:/doctor/appointments"; // redirect back to the appointments page
    }

    @GetMapping("/prescription-form/{id}")
    public String showPrescriptionForm(@PathVariable Long id, Model model) {
        System.out.println("Inside prescription form handler for ID: " + id);
        Appointment appointment = appointmentRepository.findById(id).orElseThrow();

        Prescription prescription = new Prescription();
        prescription.setAppointment(appointment);
        prescription.setPatient(appointment.getPatient());
        prescription.setDoctor(appointment.getDoctor());

        model.addAttribute("prescription", prescription);
        return "doctor/prescription-form";
    }



    @PostMapping("/prescription-submit")
    public String submitPrescription(@ModelAttribute Prescription prescription) {
        prescriptionRepository.save(prescription);
        return "redirect:/doctor/appointments";
    }




}
