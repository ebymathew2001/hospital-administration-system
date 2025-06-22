# Hospital Administration System

A Java Spring Boot web application for managing hospital workflows including Reception, Doctor, and Pharmacy modules. Designed for learning full-stack development, showcasing to recruiters, and demonstrating backend development skills.

## 🏥 Project Overview

This Hospital Administration System provides essential hospital services:

- **Reception Module**:
    - Register new patients
    - Search patients by ID
    - View patient consultation history

- **Doctor Module**:
    - View upcoming appointments
    - Add prescriptions
    - View patient medical records

- **Pharmacy Module**:
    - View prescriptions created by doctors

## 💻 Tech Stack

- **Backend**:
    - Java 21
    - Spring Boot 3
    - Spring MVC, Spring Data JPA
    - Thymeleaf (for UI rendering)
    - MySQL (Database)

- **Frontend**:
    - HTML5, CSS3, Bootstrap
    - Thymeleaf template engine


📁 Project Structure

Hospital-Administration-System/
├── src/
│   ├── main/
│   │   ├── java/com/example/hospital/
│   │   │   ├── HospitalApplication.java
│   │   │   ├── controller/
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── DoctorController.java
│   │   │   │   ├── ReceptionController.java
│   │   │   │   ├── PharmacistController.java
│   │   │   │   └── DepartmentController.java
│   │   │   ├── entity/
│   │   │   │   ├── User.java
│   │   │   │   ├── Patient.java
│   │   │   │   ├── Appointment.java
│   │   │   │   ├── Department.java
│   │   │   │   ├── Prescription.java
│   │   │   │   └── enums/
│   │   │   │       ├── Role.java
│   │   │   │       ├── Status.java
│   │   │   │       └── PrescriptionStatus.java
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── PatientRepository.java
│   │   │   │   ├── AppointmentRepository.java
│   │   │   │   ├── DepartmentRepository.java
│   │   │   │   └── PrescriptionRepository.java
│   │   │   ├── service/
│   │   │   │   ├── UserService.java
│   │   │   │   ├── PatientService.java
│   │   │   │   ├── AppointmentService.java
│   │   │   │   ├── DepartmentService.java
│   │   │   │   ├── PrescriptionService.java
│   │   │   │   └── EmailService.java
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java
│   │   │   └── dto/
│   │   │       ├── LoginRequest.java
│   │   │       ├── UserRegistrationDto.java
│   │   │       ├── PatientDto.java
│   │   │       └── AppointmentDto.java
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   ├── static/
│   │   │   │   ├── css/
│   │   │   │   │   ├── bootstrap.min.css
│   │   │   │   │   └── style.css
│   │   │   │   ├── js/
│   │   │   │   │   ├── jquery.min.js
│   │   │   │   │   ├── bootstrap.min.js
│   │   │   │   │   └── main.js
│   │   │   │   └── images/
│   │   │   │       └── logo.png
│   │   │   └── templates/
│   │   │       ├── login/
│   │   │       │   ├── login.html
│   │   │       │   └── register.html
│   │   │       ├── doctor/
│   │   │       │   ├── dashboard.html
│   │   │       │   ├── appointments.html
│   │   │       │   └── prescription-form.html
│   │   │       ├── reception/
│   │   │       │   ├── dashboard.html
│   │   │       │   ├── patient-registration.html
│   │   │       │   ├── appointment-booking.html
│   │   │       │   └── patient-list.html
│   │   │       ├── pharmacist/
│   │   │       │   ├── dashboard.html
│   │   │       │   └── prescription-list.html
│   │   │       ├── admin/
│   │   │       │   ├── dashboard.html
│   │   │       │   └── department-management.html
│   │   │       └── fragments/
│   │   │           ├── header.html
│   │   │           └── footer.html
│   │   └── webapp/
│   │       └── WEB-INF/
│   │           └── web.xml
│   └── test/java/com/example/hospital/
│       ├── HospitalApplicationTests.java
│       ├── controller/ControllerTests.java
│       ├── service/ServiceTests.java
│       └── repository/RepositoryTests.java
├── target/                 # Compiled bytecode and build artifacts
├── .gitignore              # Git ignore rules
├── pom.xml                 # Maven configuration file
├── README.md               # Project description and documentation
└── application.yml         # Optional alternative config file

