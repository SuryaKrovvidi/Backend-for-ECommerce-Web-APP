package com.example.service;

import com.example.Helper.IdGenerator;
import com.example.dao.CartItemModelDao;
import com.example.dao.CartModelDao;
import com.example.dao.OrderModelDao;
import com.example.dao.UserModelDao;
import com.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {

    @Autowired
    private OrderModelDao orderModelDao;

    @Autowired
    private UserModelDao userModelDao;

    @Autowired
    private CartModelDao cartModelDao;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private CartItemModelDao cartItemModelDao;


    public ResponseEntity<OrderModel> placeOrder(OrderModel orderModel){

        if(!(userModelDao.existsById(orderModel.getUserId()))){
            return new ResponseEntity<OrderModel>((OrderModel) null, HttpStatus.BAD_GATEWAY);
        }

        orderModel.setOrderId(idGenerator.getSaltString());
        orderModel.setTotalPrice(idGenerator.getTotalPrice(orderModel.getPrice(), orderModel.getQuantity()));
        orderModel.getTotalPrice();
        UserModel userModel = userModelDao.findById(orderModel.getUserId()).get();

        userModel.getOrdersList().add(orderModel);

        System.out.println(userModel);

        userModelDao.save(userModel);

        return new ResponseEntity<OrderModel>(orderModel, HttpStatus.OK);
    }

    public ResponseEntity<List<OrderModel>> getUserProducts(@RequestBody String userId){
        if(!(userModelDao.existsById(userId))){
            return new ResponseEntity<List<OrderModel>>((List<OrderModel>) null, HttpStatus.BAD_REQUEST);
        }

        UserModel userModel = userModelDao.findById(userId).get();
        List<OrderModel> orders = userModel.getOrdersList();
        return new ResponseEntity<List<OrderModel>>(orders, HttpStatus.OK);
    }

    public ResponseEntity<List<OrderModel>> saveProduct(String userId){
        if(!(userModelDao.existsById(userId))){
            return new ResponseEntity<List<OrderModel>>((List<OrderModel>) null, HttpStatus.BAD_REQUEST);
        }

        UserModel userModel = userModelDao.findById(userId).get();
        CartModel cartModel = userModel.getCart();

        System.out.println(userModel.getEmail());

        List<CartItemModel> cartItems = cartModel.getCartItems();
        List<OrderModel> userOrders = userModel.getOrdersList();
        List<OrderModel> ordersPlaced = new ArrayList<>();

        for(CartItemModel item : cartItems){
            String orderId = idGenerator.getSaltString();
            String productName = item.getProductName();
            int quantity = item.getQuantity();
            String totalPrice = idGenerator.getTotalPrice(item.getPrice(), item.getQuantity());
            String status = "Paid";
            String price = item.getPrice();
            OrderModel newOrder = new OrderModel(orderId, userId, productName, quantity, totalPrice, status, price);



            //ADD THIS ORDER TO THE USER'S ORDER LIST AND ALSO THE placedOrderS LIST
            ordersPlaced.add(newOrder);


        }
        for(OrderModel order : ordersPlaced){
            userOrders.add(order);
        }
        userModelDao.save(userModel);
        cartItems.clear();
        cartModelDao.save(cartModel);

        return new ResponseEntity<List<OrderModel>>(ordersPlaced, HttpStatus.OK);

    }

    public ResponseEntity<List<OrderModel>> getAllOrders(){
        List<OrderModel> allOrders = orderModelDao.findAll();
        return new ResponseEntity<List<OrderModel>>(allOrders, HttpStatus.OK);
    }


}
