package com.example.dao;

import com.example.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//the primary key used here is the user email

@Repository
public interface UserModelDao extends JpaRepository<UserModel, String> {
}
