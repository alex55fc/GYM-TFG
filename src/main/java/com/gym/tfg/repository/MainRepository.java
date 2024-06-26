package com.gym.tfg.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gym.tfg.model.User;
import com.gym.tfg.model.dto.UserGymClassDto;
import com.gym.tfg.model.dto.UserGymClassDtoRowMapper;

/**
 * Repository class for handling database operations related to user management and gym class enrollment.
 * It uses Spring's JdbcTemplate for executing SQL queries and updates.
 */
@Repository
public class MainRepository {


	@Autowired
	JdbcTemplate jdbc;

    // UserService
	public void insertNewUserRepo(User user) {
		jdbc.update("INSERT INTO users(email, password, name, surname, age, weight, gender) VALUES (?,?,?,?,?,?,?);",
				user.getEmail(), user.getPassword(),user.getName(),user.getSurname(), user.getAge(), user.getWeight(), user.getGender().toString());

	}
	public boolean deleteUserSusbcription(User user) {
		int rowsAffected = jdbc.update("UPDATE users SET subscription_id = NULL WHERE email = ? ",
				user.getEmail());
		return rowsAffected > 0;
	}
	
    // SubscriptionService
	public void updateUserSubscriptionToPremium(User user) {
		jdbc.update("UPDATE subscriptions SET subscription_name = ?, monthly_price = ?, subscription_type = ? WHERE id = ? ",
				user.getSubscription().getSubscriptionName(), user.getSubscription().getMonthlyPrice(), "PREMIUM", user.getSubscription().getId());

	}

	public void updateUserSubscriptionToBasic(User user) {
		jdbc.update("UPDATE subscriptions SET subscription_name = ?, monthly_price = ?, subscription_type = ? WHERE id = ? ",
				user.getSubscription().getSubscriptionName(), user.getSubscription().getMonthlyPrice(), "BASIC", user.getSubscription().getId());
	}


    // UserGymClassService
	public boolean isUserEnrolledInGymClass(String userEmail, int gymClassId) {
		int count = jdbc.queryForObject("SELECT COUNT(*) FROM user_gymclass WHERE user_email = ? AND gym_class_id = ?", 
				Integer.class, userEmail, gymClassId);
		return count > 0;

	}

	public void insertIntoUserGymClass(String userEmail, int gymClassId, String gymNameClass, String classWeeklyDay) {
		jdbc.update("INSERT INTO user_gymclass(user_email, gym_class_id, name_class, weekly_day) values (?, ?, ?,?);",
		userEmail, gymClassId, gymNameClass, classWeeklyDay);
	}	
	
	public boolean deleteUserGymClass(String userEmail, int gymClassId) {
		int rowsAffected = jdbc.update("DELETE FROM user_gymclass WHERE user_email = ? AND gym_class_id = ?", userEmail, gymClassId);
		return rowsAffected > 0;
	}
	
	public List<UserGymClassDto> findAllUserGymClass(String email){
		return jdbc.query("SELECT * FROM user_gymclass WHERE user_email = ?", new UserGymClassDtoRowMapper(), email);
	}
	
    // GymClassService
	public void incrementGymClassCapacityCurrent(int gymClassId) {
		jdbc.update("UPDATE gym_classes SET current_capacity = current_capacity + 1 WHERE gym_class_id = ?", gymClassId);
	}
	public void decrementGymClassCapacityCurrent(int gymClassId) {
		jdbc.update("UPDATE gym_classes SET current_capacity = current_capacity - 1 WHERE gym_class_id = ?", gymClassId);
	}



}
