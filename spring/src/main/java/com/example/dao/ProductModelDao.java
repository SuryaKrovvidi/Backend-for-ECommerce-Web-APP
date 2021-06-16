package com.example.dao;

import com.example.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductModelDao extends JpaRepository<ProductModel, String> {
}
