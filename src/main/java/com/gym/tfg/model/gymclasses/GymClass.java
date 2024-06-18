package com.gym.tfg.model.gymclasses;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a gym class in the gym system.
 */
@Entity
@Table(name="gym_classes")
public class GymClass {

	@Id
	@Column(name="gym_class_id")
	private int id;
	@Column(name="name_class")
	private String gymClassName;
	@Column(name="current_capacity")
	private int currentCapacity;
	@Column(name="total_capacity")
	private int totalCapacity;
	@Column(name="weekly_day")
	private String weeklyDay;

	
	public GymClass() {
		super();
	}


	public GymClass(int id, String gymClassName, int currentCapacity, int totalCapacity, String weeklyDay) {
		super();
		this.id = id;
		this.gymClassName = gymClassName;
		this.currentCapacity = currentCapacity;
		this.totalCapacity = totalCapacity;
		this.weeklyDay = weeklyDay;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getGymClassName() {
		return gymClassName;
	}


	public void setGymClassName(String gymClassName) {
		this.gymClassName = gymClassName;
	}


	public int getCurrentCapacity() {
		return currentCapacity;
	}


	public void setCurrentCapacity(int currentCapacity) {
		this.currentCapacity = currentCapacity;
	}


	public int getTotalCapacity() {
		return totalCapacity;
	}


	public void setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}


	public String getWeeklyDay() {
		return weeklyDay;
	}


	public void setWeeklyDay(String weeklyDay) {
		this.weeklyDay = weeklyDay;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GymClass other = (GymClass) obj;
		return id == other.id;
	}


	public Boolean isGymClassSpaceAvaliable() {
		return this.currentCapacity < this.totalCapacity;
	}
}
