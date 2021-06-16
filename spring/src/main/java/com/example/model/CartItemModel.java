package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CartItemModel {
    @Id
    private String cartItemId;
    private String productId;
    private String productName;
    private int quantity;
    private String Price;

    public CartItemModel() {
    }

    public CartItemModel(String productId, String productName, int quantity, String price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.Price = price;
    }

    public CartItemModel(String cartItemId, String productId, String productName, int quantity, String price) {
        this.cartItemId = cartItemId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.Price = price;
    }

    public String getCartItemId() {
        return this.cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return this.Price;
    }

    public void setPrice(String price) {
        this.Price = price;
    }

    public String toString() {
        return "CartItemModel{" +
                "cartItemId='" + cartItemId + '\'' +
                ", productId='" + productId + '\'' +
                ", Price'" + Price + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity  +
                '}';
    }
}
