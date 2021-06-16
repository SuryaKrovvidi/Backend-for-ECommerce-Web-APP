package com.example.service;

import com.example.dao.LoginModelDao;
import com.example.dao.UserModelDao;
import com.example.model.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginModelDao loginModelDao;

    @Autowired
    private UserModelDao userModelDao;

    public Boolean addLogin(LoginModel loginModel) {

        if(!userModelDao.existsById(loginModel.getEmail())) {
            //the user is not registered in our database, user model table
            return false;
        }

        //this situation must be handled in the front end, don't show login page if user is active
        if(loginModelDao.existsById(loginModel.getEmail())) {
            //the user is already logged in
            return true;
        }

        //save the user in login model table after checking the password
        if(loginModel.getPassword().equals(userModelDao.findById(loginModel.getEmail()).get().getPassword())) {
            loginModelDao.save(loginModel);
            return true;
        } else {
            return false;
        }
    }

    public Boolean logoutUser(String userId){
        if(!(loginModelDao.existsById(userId))){
            return true;
        }
        loginModelDao.deleteById(userId);
        return (!(loginModelDao.existsById(userId)));
    }
}
