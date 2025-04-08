package com.example.Hospital.Administration.System.contoller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
public class LoginController {


    /**
     * this method returns the view name of the login page
     *
     * @return login page name
     */
    @GetMapping("/login")
    public String showLoginPage(@RequestParam(required = false) String error, Model model) {
        // Check if user is already authenticated
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken)) {

            // Get user's role and redirect accordingly
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals("ROLE_DOCTOR")) {
                    return "redirect:/doctor/doctor-home";
                } else if (authority.getAuthority().equals("ROLE_PHARMACIST")) {
                    return "redirect:/pharmacist/pharmacist-home";
                } else if (authority.getAuthority().equals("ROLE_RECEPTIONIST")) {
                    return "redirect:/reception/reception-home";
                }
            }
            // Default redirect if no specific role match
            return "redirect:/";
        }

        // Continue with normal login page display if not authenticated
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid email or password");
        }
        return "login/loginpage";
    }
}
