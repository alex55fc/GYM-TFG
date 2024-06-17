package com.gym.tfg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.tfg.dao.GymClassDao;
import com.gym.tfg.model.gymclasses.GymClass;
import com.gym.tfg.repository.MainRepository;

@Service
public class GymClassService {
	
	@Autowired
	GymClassDao gymClassDao;
	
	@Autowired
	MainRepository repository;
	
	public boolean isGymClassWithIdExists(int gymClassId) {
		return gymClassDao.findById(gymClassId).isPresent();
	}
	public GymClass getGymClass(int gymClassId){
		return gymClassDao.findById(gymClassId).orElse(null);
	}
	public void incrementGymClassCapacityCurrent(int gymClassId) {
		repository.incrementGymClassCapacityCurrent(gymClassId);
	}
}
