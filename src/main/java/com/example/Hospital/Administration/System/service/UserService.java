package com.example.Hospital.Administration.System.service;

import com.example.Hospital.Administration.System.entity.User;
import com.example.Hospital.Administration.System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


   public boolean userRegistration(User user){
       if(userRepository.findByEmail(user.getEmail()).isPresent()){
           return false;
       }
       userRepository.save(user);
       return true;
   }
}
