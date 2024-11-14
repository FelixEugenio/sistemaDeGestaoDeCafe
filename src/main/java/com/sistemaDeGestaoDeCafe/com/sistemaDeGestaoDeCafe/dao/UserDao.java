package com.sistemaDeGestaoDeCafe.com.sistemaDeGestaoDeCafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaDeGestaoDeCafe.com.sistemaDeGestaoDeCafe.POJO.User;



public interface UserDao extends JpaRepository<User, Integer> {
    

}
