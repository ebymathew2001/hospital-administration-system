package com.example.Hospital.Administration.System.repository;


 import com.example.Hospital.Administration.System.entity.Role;
 import com.example.Hospital.Administration.System.entity.User;
 import org.springframework.data.jpa.repository.JpaRepository;

 import java.util.List;
 import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
    boolean existsByRole(Role role);
    List<User> findByRole(Role role);
    Optional<User> findByName(String name);



}
