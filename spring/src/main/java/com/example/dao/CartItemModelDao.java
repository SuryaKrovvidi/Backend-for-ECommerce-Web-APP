package com.example.dao;

import com.example.model.CartItemModel;
import com.example.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemModelDao extends JpaRepository<CartItemModel, String> {
}
