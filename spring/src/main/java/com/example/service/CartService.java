package com.example.service;

import com.example.Helper.IdGenerator;
import com.example.dao.CartItemModelDao;
import com.example.dao.CartModelDao;
import com.example.dao.ProductModelDao;
import com.example.dao.UserModelDao;
import com.example.model.CartItemModel;
import com.example.model.CartModel;
import com.example.model.ProductModel;
import com.example.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartModelDao cartModelDao;

    @Autowired
    CartItemModelDao cartItemModelDao;

    @Autowired
    ProductModelDao productModelDao;

    @Autowired
    UserModelDao userModelDao;

    @Autowired
    IdGenerator idGenerator;

//    public ResponseEntity<CartItemModel> addToCart(int quantity, String userId, String productId){
//        if(!(productModelDao.existsById(productId))){
//            return new ResponseEntity<CartItemModel>((CartItemModel) null, HttpStatus.BAD_REQUEST);
//        }
//
//        ProductModel productModel = productModelDao.findById(productId).get();
//        UserModel userModel = userModelDao.findById(userId).get();
//        CartModel cartModel = userModel.getCart();
//
//        CartItemModel cartItemModel = new CartItemModel();
//        cartItemModel.setCartItemId(idGenerator.getSaltString());
//        cartItemModel.setQuantity(quantity);
//        cartItemModel.setPrice(productModel.getPrice());
//        cartItemModel.setProductName(productModel.getProductName());
//
//        cartModel.getCartItems().add(cartItemModel);
//        cartModelDao.save(cartModel);
//
//        return new ResponseEntity<CartItemModel>(cartItemModel, HttpStatus.OK);
//    }

    public ResponseEntity<CartItemModel> addToCart(CartItemModel cartItemModel, String userId){

        System.out.println(cartItemModel);

        if(!(productModelDao.existsById(cartItemModel.getProductId()))){
            return new ResponseEntity<CartItemModel>((CartItemModel) null, HttpStatus.BAD_REQUEST);
        }

        if(!(userModelDao.existsById(userId))){
            return new ResponseEntity<CartItemModel>((CartItemModel) null, HttpStatus.BAD_REQUEST);
        }

        UserModel userModel = userModelDao.findById(userId).get();
        CartModel cartModel = userModel.getCart();

        System.out.println(cartModel.getCartId());
        //CHECK IF THE ITEM IS ALREADY IN THE CART
        for (CartItemModel cartItem: cartModel.getCartItems()) {
            if(cartItem.getProductId().equals(cartItemModel.getProductId())){
                return new ResponseEntity<CartItemModel>((CartItemModel) null, HttpStatus.BAD_REQUEST);
            }
        }

        cartItemModel.setCartItemId(idGenerator.getSaltString());

        cartModel.getCartItems().add(cartItemModel);

        cartModelDao.save(cartModel);

        System.out.println(cartItemModel.toString());

        CartItemModel updatedCartItem = cartItemModelDao.findById(cartItemModel.getCartItemId()).get();

        System.out.println(updatedCartItem.toString());


        return new ResponseEntity<CartItemModel>((CartItemModel) updatedCartItem, HttpStatus.OK);
    }

    public ResponseEntity<List<CartItemModel>> showCart(String userId){
        if(!(userModelDao.existsById(userId))){
            return new ResponseEntity<List<CartItemModel>>((List<CartItemModel>) null, HttpStatus.BAD_REQUEST);
        }

        List<CartItemModel> cartItems = userModelDao.findById(userId).get().getCart().getCartItems();

        return new ResponseEntity<List<CartItemModel>>(cartItems, HttpStatus.OK);
    }

    public ResponseEntity<CartItemModel> deleteCartItem(String cartItemId){
        if(!(cartItemModelDao.existsById(cartItemId))){
            return new ResponseEntity<CartItemModel>((CartItemModel) null, HttpStatus.BAD_REQUEST);
        }

        CartItemModel deletedCartItem = cartItemModelDao.findById(cartItemId).get();

        cartItemModelDao.deleteById(cartItemId);

        return new ResponseEntity<CartItemModel>(deletedCartItem, HttpStatus.OK);

    }


}
