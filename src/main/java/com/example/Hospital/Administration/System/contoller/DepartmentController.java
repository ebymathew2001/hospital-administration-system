package com.example.Hospital.Administration.System.contoller;

import com.example.Hospital.Administration.System.entity.Department;
import com.example.Hospital.Administration.System.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 *
 * Controller responsible for managing department related operations
 * including create,view,update,delete department
 */
@Controller
@RequestMapping("reception")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * displays the department form.
     * @param model is used to bind the department fields to the form.
     * @return The department form view.
     */
    @GetMapping("/department-form")
    public String showForm(Model model){
        model.addAttribute("department",new Department());
        return "reception/department-form";
    }


    /**
     * Handles the submission of the department form save the new department to database
     * @param department Spring automatically binds form fields to department object using @ModelAttribute.
     * @param redirectAttributes used to pass flash messages to the user after a redirect
     * @return Redirects to the department form with success/error message.
     */
    @PostMapping("/department-form")
    public String addDepartment(@ModelAttribute Department department, RedirectAttributes redirectAttributes){
        boolean isSaved=departmentService.saveDepartment(department);
        if(!isSaved){
            redirectAttributes.addFlashAttribute("error","Department already exists");
        }
        else {
            redirectAttributes.addFlashAttribute("message", "Department added successfully!");

        }
        return "redirect:/reception/department-form";
    }

    /**
     * displays all the departments from the database
     * @param model Holds the list of departments to be displayed on the view
     * @return the department list view-page
     */
    @GetMapping("/department-view")
    public String viewDepartment(Model model){
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments",departments);
        return "reception/department-view";
    }

    /**
     * displays the department form with pre-filled existing data
     *  @param departmentId the id of the department to be edited
     * @param model stores the department object and binds to the form
     * @return the department form viw with pre-filled existing data for editing
     */
    @GetMapping("/department/edit/{departmentId}")
    public String showEditForm(@PathVariable Long departmentId, Model model){
        Department department=departmentService.getDepartmentByDepartmentId(departmentId);
        model.addAttribute("department",department);
        return "reception/department-form";
    }

    /**
     * Handles the submission of the edit department form and save the changes to database
     * @param department Spring will bind the form data to department object through ModelAttribute
     * @param redirectAttributes used to pass a flash message to user(success/error)
     * @return redirect to department-view page with a message error/success
     */
    @PostMapping("/department/save")
    public String editDepartment(@ModelAttribute Department department,RedirectAttributes redirectAttributes){
        boolean isSave=departmentService.updateDepartment(department);
        if(!isSave) {
            redirectAttributes.addFlashAttribute("error", "The department already exists");
        }
        else{
            redirectAttributes.addFlashAttribute("message","The department added successfully");
        }
        return "redirect:/reception/department-view";
    }

    /**
     * Handles the deletion of the departments
     * @param departmentId the id of the department to be deleted
     * @param redirectAttributes used to pass a flash message to user (success/error)
     * @return redirect to the department-view with a message (success/error)
     */
    @PostMapping("/department/delete/{departmentId}")
    public String deleteDepartment(@PathVariable Long departmentId,RedirectAttributes redirectAttributes){
        boolean isDeleted= departmentService.deleteDepartmentById(departmentId);
        if(isDeleted) {
            redirectAttributes.addFlashAttribute("message", "The department is deleted successfully");
        }

        else{
            redirectAttributes.addFlashAttribute("error","the department didn't exist");
        }
        return "redirect:/reception/department-view";
    }


























}
