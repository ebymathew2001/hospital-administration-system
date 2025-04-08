package com.example.Hospital.Administration.System.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pharmacist")
public class PharmacistController {

    @GetMapping("/pharmacist-home")
    public String showPharmacistHome(){
        return "/pharmacist/pharmacist-home";
    }

}
