package com.example.Hospital.Administration.System.contoller;

import com.example.Hospital.Administration.System.entity.Prescription;
import com.example.Hospital.Administration.System.repository.PrescriptionRepository;
import com.example.Hospital.Administration.System.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pharmacy")
public class PharmacistController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/pharmacist-home")
    public String showPharmacistHome(){
        return "/pharmacist/pharmacist-home";
    }

    @GetMapping("/prescriptions")
    public String viewPendingPrescriptions(Model model) {
        // Get all PENDING prescriptions with newest first
        List<Prescription> pendingPrescriptions = prescriptionService.getPendingPrescriptions();
        model.addAttribute("prescriptions", pendingPrescriptions);
        return "pharmacist/prescription-list";
    }

}
