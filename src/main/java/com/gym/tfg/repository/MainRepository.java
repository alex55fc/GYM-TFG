package com.gym.tfg.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gym.tfg.model.User;

@Repository
public class MainRepository {
	

	@Autowired
	JdbcTemplate jdbc;
	
	public void insertNewUserRepo(User user) {
		jdbc.update("INSERT INTO users(email, password, name, surname, age, weight, gender) VALUES (?,?,?,?,?,?,?);",
				user.getEmail(), user.getPassword(),user.getName(),user.getSurname(), user.getAge(), user.getWeight(), user.getGender().toString());
		
	}
	public void updateUserSubscriptionToPremium(User user) {
		jdbc.update("UPDATE subscriptions SET subscription_name = ?, monthly_price = ?, subscription_type = ? WHERE id = ? ",
				user.getSubscription().getSubscriptionName(), user.getSubscription().getMonthlyPrice(), "PREMIUM", user.getSubscription().getId());
		
	}
	

}
