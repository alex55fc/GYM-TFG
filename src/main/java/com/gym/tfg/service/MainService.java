package com.gym.tfg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.tfg.dao.SubscriptionDao;
import com.gym.tfg.dao.UserDao;
import com.gym.tfg.model.User;
import com.gym.tfg.model.subscription.Subscription;
import com.gym.tfg.repository.MainRepository;

@Service
public class MainService {
	
	@Autowired
	SubscriptionDao subDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	MainRepository repository;
	
	public void insertNewUser(User user) {
		repository.insertNewUserRepo(user);
	}
	
	public Subscription saveNewSubscription(Subscription subscription) {
		return subDao.save(subscription);
	}
	
	public void updateUserSubscription(User user) {
		userDao.save(user);
	}
}
