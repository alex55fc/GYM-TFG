package com.gym.tfg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.tfg.model.subscription.Subscription;

public interface SubscriptionDao extends JpaRepository<Subscription, Integer> {
	
}	
