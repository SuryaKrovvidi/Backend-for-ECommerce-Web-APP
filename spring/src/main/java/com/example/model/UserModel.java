package com.example.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserModel {

    @Id
    private String email;
    private String password;
    private String username;
    private String mobileNumber;
    private Boolean active;
    private String role;


    //    @JoinColumn(name = "email", referencedColumnName = "email")
    @OneToOne(targetEntity = CartModel.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private CartModel cart;

    //1) this means along with a user, all the orders are also saved to the database
    //2) the email of a user will be used as the foreign key in the order

//    @JoinColumn(name = "email", referencedColumnName = "email")

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "email", referencedColumnName = "email")
    private List<OrderModel> ordersList = new ArrayList<>();

    public UserModel() {
    }

    public UserModel(String email){
        this.email = email;
    }

    public UserModel(String email, String password, String username, String mobileNumber, Boolean active, String role, CartModel cart, List<OrderModel> ordersList) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.active = active;
        this.role = role;
        this.cart = cart;
        this.ordersList = ordersList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public CartModel getCart() {
        return cart;
    }

    public void setCart(CartModel cart) {
        this.cart = cart;
    }

    public List<OrderModel> getOrdersList() {
        return this.ordersList;
    }

    public void setOrdersList(List<OrderModel> ordersList) {
        this.ordersList = ordersList;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", active=" + active +
                ", role='" + role + '\'' +
                ", cart=" + cart +
                ", ordersList=" + ordersList +
                '}';
    }
}
