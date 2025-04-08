package com.example.Hospital.Administration.System.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @GetMapping("doctor-home")
    public String showDoctorHome(){
        return "doctor/doctor-home";
    }
}
