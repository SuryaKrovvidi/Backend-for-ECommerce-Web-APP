package com.example.controller;

import com.example.model.CartItemModel;
import com.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class CartController {

    @Autowired
    CartService cartService;

    //PASS CART ITEM IN THE BODY

    @PostMapping(value = "/home/{id}")                  //Pass Price in the cart item model as 'price'
    ResponseEntity<CartItemModel> addToCart(@RequestBody CartItemModel cartItemModel,
                                            @PathVariable("id") String userId){
        System.out.println(userId);
        return cartService.addToCart(cartItemModel, userId);
    }


    @PostMapping(value = "/cart/{id}")
    ResponseEntity<List<CartItemModel>> showCart(@PathVariable("id") String userId){
        return cartService.showCart(userId);
    }


    @PostMapping(value = "/cart/delete")
    ResponseEntity<CartItemModel> deleteCartItem(@RequestBody String cartItemId){   //Pass a plain String
        System.out.println(cartItemId);
        return cartService.deleteCartItem(cartItemId);
    }
}
