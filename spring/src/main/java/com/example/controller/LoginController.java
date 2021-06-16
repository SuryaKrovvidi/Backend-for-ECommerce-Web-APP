package com.example.controller;

import com.example.model.LoginModel;
import com.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Boolean checkUser(@RequestBody LoginModel loginModel) {
        //this will store the loginModel in our database, means this user is currently logged in
        return loginService.addLogin(loginModel);
    }

    @PostMapping("/logout")
    public Boolean logoutUser(@RequestBody String userId){
        return loginService.logoutUser(userId);
    }

}

