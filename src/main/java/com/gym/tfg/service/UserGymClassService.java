package com.gym.tfg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.tfg.dao.GymClassDao;
import com.gym.tfg.model.dto.UserGymClassDto;
import com.gym.tfg.repository.MainRepository;

/**
 * Service class to handle operations related to user gym class enrollments.
 */
@Service
public class UserGymClassService {
	
	@Autowired
	GymClassDao gymClassDao;
	
	@Autowired
	MainRepository repository;
	

	public boolean userInGymClass(String userEmail, int gymClassId) {
		return repository.isUserEnrolledInGymClass(userEmail, gymClassId);
	}
	
	public void insertUserGymClass(String userEmail, int gymClassId, String gymNameClass, String classWeeklyDay ) {
		repository.insertIntoUserGymClass(userEmail, gymClassId, gymNameClass, classWeeklyDay);
	}
	
	public boolean deleteUserGymClass(String userEmail, int gymClassId) {
		return repository.deleteUserGymClass(userEmail, gymClassId);
	}
	public void decrementUserGymClass(int gymClassId) {
		repository.decrementGymClassCapacityCurrent(gymClassId);
	}
	
	public List<UserGymClassDto> listAllUserGymClasses(String userEmail) {
		return repository.findAllUserGymClass(userEmail);
	}
}
