package com.gym.tfg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.tfg.dao.SubscriptionDao;
import com.gym.tfg.model.subscription.Subscription;

@Service
public class SubscriptionService {
	
	@Autowired
	SubscriptionDao subDao;
	
	public Subscription saveNewSubscription(Subscription subscription) {
		return subDao.save(subscription);
	}
	

}
