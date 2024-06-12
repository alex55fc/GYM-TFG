package com.gym.tfg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.tfg.model.User;

public interface UserDao extends JpaRepository<User, String> {
	
}
