package com.example.Hospital.Administration.System.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    /**
     * this method returns the view name of the login page
     * @return login page name
     */
    @GetMapping("/login")
    public String showLoginPage(){
        return "/login/loginpage";
    }
}
