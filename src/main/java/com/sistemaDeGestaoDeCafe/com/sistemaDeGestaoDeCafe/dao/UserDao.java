package com.sistemaDeGestaoDeCafe.com.sistemaDeGestaoDeCafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.sistemaDeGestaoDeCafe.com.sistemaDeGestaoDeCafe.POJO.User;



public interface UserDao extends JpaRepository<User, Integer> {
    org.apache.catalina.User findByEmailId(@Param("email") String email);   

}
