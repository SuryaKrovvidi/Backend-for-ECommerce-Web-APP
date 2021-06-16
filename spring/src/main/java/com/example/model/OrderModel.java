package com.example.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Random;

@Entity
public class OrderModel {

    @Id
    private String orderId;

    private String ProductName;
    private int quantity;
    private String totalPrice;
    private String Status;
    private String Price;

//    @OneToOne(targetEntity = UserModel.class, fetch = FetchType.EAGER)
//    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
//    THE SERVICE placeOrder DOESN'T WORK AS EXPECTED IF THESE LINES ARE NOT COMMENTED
    private String userId;

    public OrderModel(){
    }

    public OrderModel(String orderId, String userId, String productName, int quantity, String totalPrice, String status, String price) {
        this.orderId = orderId;
        this.userId = userId;
        this.ProductName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.Status = status;
        this.Price = price;
    }


    // Without Order Id
    public OrderModel(String userId, String productName, int quantity, String totalPrice, String status, String price) {
        this.userId = userId;
        this.ProductName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.Status = status;
        this.Price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "orderId='" + orderId + '\'' +
                ", userId=" + userId +
                ", ProductName='" + ProductName + '\'' +
                ", quantity=" + quantity +
                ", totalPrice='" + totalPrice + '\'' +
                ", Status='" + Status + '\'' +
                ", Price='" + Price + '\'' +
                '}';
    }


}
