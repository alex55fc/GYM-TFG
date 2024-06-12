package com.gym.tfg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.tfg.dao.UserDao;
import com.gym.tfg.model.User;
import com.gym.tfg.repository.MainRepository;

@Service
public class UserService {
	
	@Autowired
	MainRepository repository;
	
	@Autowired
	UserDao userDao;
	
	public void insertNewUser(User user) {
		repository.insertNewUserRepo(user);
	}
	
	public void createUserSubscription(User user) {
		userDao.save(user);
	}
	public void updateUser(User user) {
		userDao.save(user);
	}
	

	

}
