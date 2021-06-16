package com.example.dao;

import com.example.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderModelDao extends JpaRepository<OrderModel, String> {
}
