package com.gym.tfg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.tfg.model.gymclasses.GymClass;

public interface GymClassDao extends JpaRepository<GymClass, Integer> {
	
}
