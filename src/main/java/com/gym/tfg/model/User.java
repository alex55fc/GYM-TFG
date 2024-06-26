package com.gym.tfg.model;

import com.gym.tfg.model.subscription.Subscription;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents a user in the gym system.
 */
@Entity
@Table(name="users")
public class User {

	@Id
	private String email;
	private String password;
	private String name;
	private String surname;
	private int age;
	private double weight;
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@ManyToOne
	@JoinColumn(name="subscription_id", nullable=true)
	private Subscription subscription;



	public User() {
		super();
	}

	public User(String email, String password, String name, String surname, int age) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.age = age;
	}

	public User(String email, String password, String name, String surname, int age, double weight, Gender gender) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.weight = weight;
		this.gender = gender;
		this.subscription = null;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", name=" + name + ", surname=" + surname + ", age="
				+ age + ", weight=" + weight + ", gender=" + gender + "]";
	}


}
