package com.gym.tfg.model.dto;

/**
 * Data Transfer Object (DTO) representing a user's enrollment in a gym class.
 */
public class UserGymClassDto {
	private String emailUser;
	private int gymClassId;
	
	
	public UserGymClassDto() {
		super();
	}
	public UserGymClassDto(String emailUser, int gymClassId) {
		super();
		this.emailUser = emailUser;
		this.gymClassId = gymClassId;
	}
	public String getEmailUser() {
		return emailUser;
	}
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	public int getGymClassId() {
		return gymClassId;
	}
	public void setGymClassId(int gymClassId) {
		this.gymClassId = gymClassId;
	}
	
}
