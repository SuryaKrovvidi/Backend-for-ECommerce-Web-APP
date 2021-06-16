package com.example.dao;

import com.example.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartModelDao extends JpaRepository<CartModel, String> {
}
