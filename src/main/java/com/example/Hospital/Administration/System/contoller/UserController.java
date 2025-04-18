package com.example.Hospital.Administration.System.contoller;


import com.example.Hospital.Administration.System.entity.Department;
import com.example.Hospital.Administration.System.entity.Role;
import com.example.Hospital.Administration.System.entity.User;
import com.example.Hospital.Administration.System.repository.UserRepository;
import com.example.Hospital.Administration.System.service.DepartmentService;
import com.example.Hospital.Administration.System.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controller that handles login related things and registration of users etc
 */
@Controller
public class UserController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;




    /**
     * This method is for handling the showing of the registration page to user
     * @param model will bind user object to the fields of the form
     * @return the name of the user registration page
     */
    @GetMapping("/registration")
    public String showRegistrationPage(Model model){
        model.addAttribute("user",new User());
        List<Department> departments=departmentService.getAllDepartments();
        boolean receptionistExists = userRepository.existsByRole(Role.RECEPTIONIST); // ✅ This is correct

        model.addAttribute("receptionistExists", receptionistExists);
        model.addAttribute("department",departments);
        return "login/user-registration";
    }

    @PostMapping("/registration")
    public String userRegistration(@Valid @ModelAttribute User user, BindingResult result, RedirectAttributes redirectAttributes,Model model){
        if(result.hasErrors()){
            return "redirect:/registration";
        }
        String status=userService.userRegistration(user);
        if(!"SUCCESS".equals(status)) {
            redirectAttributes.addFlashAttribute("user", user);
            if ("EMAIL_EXISTS".equals(status)) {
                redirectAttributes.addFlashAttribute("emailError", "This email is already registered");
            } else if ("PHONE_EXISTS".equals(status)) {
                redirectAttributes.addFlashAttribute("phoneError", "This phone number is already registered");
            }
            return "redirect:/registration";
        }
        redirectAttributes.addFlashAttribute("success","Successfully registered");

        return "redirect:/registration";
    }

}
