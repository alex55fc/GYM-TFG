package com.gym.tfg.model.subscription;

import jakarta.persistence.Column;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

/**
 * Represents a subscription in the gym system.
 */
@Entity
@Table(name="subscriptions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="subscription_type")
public abstract class Subscription {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	@Column(name="subscription_name")
	protected String subscriptionName;
	@Column(name="monthly_price")
	protected double monthlyPrice;

	
	public Subscription() {
		super();
	}

	public Subscription(String subscriptionName, double monthlyPrice) {
		super();
		this.subscriptionName = subscriptionName;
		this.monthlyPrice = monthlyPrice;
	}

	public String getSubscriptionName() {
		return subscriptionName;
	}

	public void setSubscriptionName(String subscriptionName) {
		this.subscriptionName = subscriptionName;
	}

	public double getMonthlyPrice() {
		return monthlyPrice;
	}

	public void setMonthlyPrice(double monthlyPrice) {
		this.monthlyPrice = monthlyPrice;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public abstract double calculateFirstMonth();
	public abstract double calculateMonthlyPrice();
	
}
