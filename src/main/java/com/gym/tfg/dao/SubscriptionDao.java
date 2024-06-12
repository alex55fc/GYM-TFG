package com.gym.tfg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gym.tfg.model.subscription.Subscription;

import jakarta.transaction.Transactional;

public interface SubscriptionDao extends JpaRepository<Subscription, Integer> {
	
}
