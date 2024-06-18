package com.gym.tfg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.tfg.dao.UserDao;
import com.gym.tfg.model.User;
import com.gym.tfg.repository.MainRepository;

/**
 * Service class to handle CRUD operations for user entities either through the DAO or by forwarding to the repository
 */
@Service
public class UserService {
	
	@Autowired
	MainRepository repository;
	
	@Autowired
	UserDao userDao;
	
	public void insertNewUser(User user) {
		repository.insertNewUserRepo(user);
	}
	
	public void createUserSubscription(User user) {
		userDao.save(user);
	}
	public void updateUser(User user) {
		userDao.save(user);
	}
	
	public boolean deleteUserSusbcription(User user) {
		return repository.deleteUserSusbcription(user);
	}
	public User getUserFromDatabaseFromDB(String email) {
		return userDao.findById(email).orElse(null);
	}
	
	// User verification methods
	public boolean isUserWithEmailExists(String email) {
		return userDao.findById(email).orElse(null) != null;
	}
	
	public boolean isPasswordMatchingEmail(User user) {
		User userAux = userDao.findById(user.getEmail()).orElse(null);
		if(user.getPassword().equals(userAux.getPassword())) {
			return true;
		}
		return false;
	}
	

}
