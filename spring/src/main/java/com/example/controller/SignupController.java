package com.example.controller;

import com.example.model.UserModel;
import com.example.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class SignupController {

    @Autowired
    SignupService signupService;

    @PostMapping("/signup")
    public Boolean saveUser(@RequestBody UserModel user) {
        //this will store the user in out database
        System.out.println("Hit!!");
        return signupService.addUser(user);
    }

}
