package com.example.Hospital.Administration.System.entity;


import jakarta.persistence.*;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="department_id")
    private Long departmentId;

    @Column(nullable = false,unique = true)
    private String departmentName;

    public Department(){}

    public Department(String departmentName){
        this.departmentName=departmentName;
    }

    public Long getDepartmentId(){
        return departmentId;
    }

    public String getDepartmentName(){
        return departmentName;
    }

    public void setDepartmentId(Long departmentId){
        this.departmentId=departmentId;
    }

    public void setDepartmentName(String departmentName){
        this.departmentName= departmentName;
    }

}
