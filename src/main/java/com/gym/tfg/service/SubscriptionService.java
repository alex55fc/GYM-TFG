package com.gym.tfg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.tfg.dao.SubscriptionDao;
import com.gym.tfg.model.User;
import com.gym.tfg.model.subscription.Subscription;
import com.gym.tfg.repository.MainRepository;

@Service
public class SubscriptionService {

	@Autowired
	SubscriptionDao subDao;

	@Autowired
	MainRepository repository;

	public Subscription saveNewSubscription(Subscription subscription) {
		return subDao.save(subscription);
	}

	//metodo no usado de momento 
	public Subscription obtainSusbcriptionById(Integer id) {
		return subDao.findById(id).orElse(null);
	}

	public void upgradeUserSubscriptionToPremium(User user) {
		repository.updateUserSubscriptionToPremium(user);
	}

	public void downgradeUserSubscriptionToBasic(User user) {
		repository.updateUserSubscriptionToBasic(user);
	}

	public void deleteSubscriptionFromUser(User user) {
		subDao.deleteById(user.getSubscription().getId());
	}
	
	public boolean findSubscription(User user) {
		Boolean subDeleted = false;
		if(subDao.findById(user.getSubscription().getId()).orElse(null) == null) {
			subDeleted = true;
		}
		return subDeleted;
	}

}
