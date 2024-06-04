package com.gym.tfg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.tfg.model.User;
import com.gym.tfg.repository.MainRepository;

@Service
public class MainService {
	
	@Autowired
	MainRepository repository;
	
	public void insertNewUser(User user) {
		repository.insertNewUserRepo(user);
	}
}
