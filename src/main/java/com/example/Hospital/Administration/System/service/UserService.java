package com.example.Hospital.Administration.System.service;

import com.example.Hospital.Administration.System.entity.User;
import com.example.Hospital.Administration.System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


   public String userRegistration(User user){
       if(userRepository.findByEmail(user.getEmail()).isPresent()){
           return "EMAIL_EXISTS";
       }
       if(userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()){
           return "PHONE_EXISTS";
       }
       userRepository.save(user);
       return "SUCCESS";
   }
}
