package com.gym.tfg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.tfg.model.gymclasses.GymClass;

/**
 * Interface for accessing gym class data in the database.
 */
public interface GymClassDao extends JpaRepository<GymClass, Integer> {
	
}
