package com.example.Hospital.Administration.System.service;

import com.example.Hospital.Administration.System.entity.Department;
import com.example.Hospital.Administration.System.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Class that Manages the business logic related to Department operations
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;


    /**
     * saves a new department to the database
     * @param department The department object that passed from the controller for adding
     * @return {@code true} if the department was successfully added ,{@code false} if it already exists.
     */
    public boolean saveDepartment(Department department){
        if(departmentRepository.findByDepartmentName(department.getDepartmentName()).isPresent()){
            return false;
        }
        departmentRepository.save(department);
        return true;
    }

    /**
     * Retrieves all departments from the database
     * @return A list of all departments form the department table
     */
    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    /**
     * Retrieves a Department from the database based on the id
     * @param departmentId passed from the controller for finding department with that particular id
     * @return return a department object
     */
    public Department getDepartmentByDepartmentId(Long departmentId){
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }


    /**
     * Updates an existing Department
     * @param department an object passed form the controller for editing
     * @return {@code false} if a department with the same name already exists,
     *         {@code true}  if the department was successfully updated.
     */
    public boolean updateDepartment(Department department){
        if(departmentRepository.findByDepartmentName(department.getDepartmentName()).isPresent()){
            return false;
        }
        Department existingDept = departmentRepository.findById(department.getDepartmentId())
                .orElseThrow(()-> new RuntimeException("Department not found"));
        existingDept.setDepartmentName(department.getDepartmentName());
        departmentRepository.save(existingDept);
        return true;
    }

    /**
     * Deletes the department
     * @param departmentId id passed from the controller for deleting
     * @return {@code false}if department is not deleted
     *         {@code true}if department is deleted
     */
    public Boolean deleteDepartmentById(Long departmentId){
        if(departmentRepository.existsById(departmentId)){
            departmentRepository.deleteById(departmentId);
            return true;
        }
        return false;
    }


}
