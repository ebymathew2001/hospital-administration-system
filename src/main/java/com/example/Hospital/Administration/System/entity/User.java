package com.example.Hospital.Administration.System.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank(message = "Name is required")
    @Size(min =3,max = 50,message = "Name must be between 3 and 50 characters")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(nullable = false,unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=6,message = "Password must be at least 6 characters long")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    @Column(nullable = false,unique = true)
    private String phoneNumber;


    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    @ManyToOne
    @JoinColumn(name="department_id",nullable = true)
    private Department department;

    public User(){}

    public User(String name,String email,String password,String phoneNumber,Role role,Department department) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.department = department;
    }

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId=userId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;

    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role){
        this.role=role;
    }

    public Department getDepartment(){
        return department;
    }

    public void setDepartment(Department department){
        this.department=department;
    }
}
