package com.example.Hospital.Administration.System.contoller;


import com.example.Hospital.Administration.System.entity.Department;
import com.example.Hospital.Administration.System.service.ReceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controller for handling reception related operations
 */
@Controller
@RequestMapping("/reception")
public class ReceptionController {

    @Autowired
    private ReceptionService receptionService;

    /**
     * This method returns the view name of the receptionHome
     * @return the reception home page
     */
    @GetMapping("/reception-home")
    public String showReceptionHome(){
        return "reception/reception-home";
    }

}
