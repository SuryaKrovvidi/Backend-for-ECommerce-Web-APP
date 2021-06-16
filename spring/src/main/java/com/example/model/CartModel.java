package com.example.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart_table")
public class CartModel {

    @Id
    private String cartId;

    @OneToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cart")
    @JoinColumn(name = "email")
    private UserModel userModel;

    public List<CartItemModel> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemModel> cartItems) {
        this.cartItems = cartItems;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartId", referencedColumnName = "cartId")
    private List<CartItemModel> cartItems;

    public CartModel(){
    }

    public CartModel(String cartId, UserModel userModel, List<CartItemModel> cartItems) {
        this.cartId = cartId;
        this.userModel = userModel;
        this.cartItems = cartItems;
    }

    public void setUserModel(UserModel userModel){
        this.userModel = userModel;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

    public UserModel getUserModel() {
        return userModel;
    }
}
