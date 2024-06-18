package com.gym.tfg.model.dto;

/**
 * Data Transfer Object (DTO) representing a user's enrollment in a gym class.
 */
public class UserGymClassDto {
	private String emailUser;
	private int gymClassId;
	private String gymClassName;
	private String gymClassWeeklyDay;
	
	public UserGymClassDto() {
		super();
	}


	public UserGymClassDto(String emailUser, int gymClassId, String gymClassName, String gymClassWeeklyDay) {
		super();
		this.emailUser = emailUser;
		this.gymClassId = gymClassId;
		this.gymClassName = gymClassName;
		this.gymClassWeeklyDay = gymClassWeeklyDay;
	}


	public String getGymClassWeeklyDay() {
		return gymClassWeeklyDay;
	}


	public void setGymClassWeeklyDay(String gymClassWeeklyDay) {
		this.gymClassWeeklyDay = gymClassWeeklyDay;
	}


	public String getGymClassName() {
		return gymClassName;
	}

	public void setGymClassName(String gymClassName) {
		this.gymClassName = gymClassName;
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
