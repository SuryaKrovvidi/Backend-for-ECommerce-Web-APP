package com.example.service;

import com.example.Helper.IdGenerator;
import com.example.dao.CartModelDao;
import com.example.dao.UserModelDao;
import com.example.model.CartModel;
import com.example.model.UserModel;
import org.hibernate.id.uuid.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    @Autowired
    private UserModelDao userModelDao;


    @Autowired
    private CartModelDao cartModelDao;

    @Autowired
    private IdGenerator idGenerator;

    public Boolean addUser(UserModel userModel) {
        //already existing user
        if(userModelDao.existsById(userModel.getEmail())) {
            return false;
        }
        //else save in database
        CartModel cartModel = new CartModel();

        cartModel.setCartId(idGenerator.getSaltString());

        userModel.setCart(cartModel);

        System.out.println(cartModel);

        userModelDao.save(userModel);
        cartModelDao.save(cartModel);

        return true;
    }

}
