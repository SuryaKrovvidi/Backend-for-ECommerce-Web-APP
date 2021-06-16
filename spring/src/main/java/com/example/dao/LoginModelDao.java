package com.example.dao;

import com.example.model.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginModelDao extends JpaRepository<LoginModel, String> {
}
