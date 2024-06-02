package com.gym.tfg.model;

public class User {
	
	private String email;
	private String password;
	private String name;
	private String surname;
	private int age;
	private double weight;
	private Gender gender;
	
	
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
	
	
}