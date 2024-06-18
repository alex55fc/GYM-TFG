package com.gym.tfg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.tfg.model.subscription.Subscription;

/**
 * Interface for accessing subscription data in the database.
 */
public interface SubscriptionDao extends JpaRepository<Subscription, Integer> {
	
}	
