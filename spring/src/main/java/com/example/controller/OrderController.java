package com.example.controller;

import com.example.model.OrderModel;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@CrossOrigin("*")
@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    OrderController(OrderService orderService){
        this.orderService = orderService;
    }


    //PASS ORDER MODEL AS A JSON OBJECT
    @PostMapping("/placeOrder")
    ResponseEntity<OrderModel> placeOrder(@RequestBody OrderModel orderModel){
        return orderService.placeOrder(orderModel);
    }

    //PASS THE USER ID AS A STRING
    @PostMapping("/orders")
    ResponseEntity<List<OrderModel>> getUserProducts(@RequestBody String userId){
        System.out.println(userId);
        return orderService.getUserProducts(userId);
    }

    //PASS THE CART ID AS A STRING
    @PostMapping("/saveOrder")
    ResponseEntity<List<OrderModel>> saveProduct(@RequestBody String userId){
        return orderService.saveProduct(userId);
    }

    @GetMapping("/admin/orders")
    ResponseEntity<List<OrderModel>> getAllOrders(){
        return orderService.getAllOrders();
    }






}
