package com.gym.tfg.model.subscription;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Represents a basic subscription in the gym system.
 */
@Entity
@DiscriminatorValue("BASIC")
public class BasicSubscription extends Subscription {

	private static final double ENROLLMENT = 20.00;
	private static final double BASIC_MONTHLY_PRICE = 20.00;

	public BasicSubscription() {
		super("BasicSubscription", BASIC_MONTHLY_PRICE);
	}
	/**
	 * Constructs a BasicSubscription object with the specified ID.
	 * This constructor is for special use, as it is intended to create a subscription
	 * with a specific ID instead of using the auto-incremented ID.
	 */
	public BasicSubscription(int id) {
		super("BasicSubscription", BASIC_MONTHLY_PRICE);
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "BasicSubscription [id=" + id + ", subscriptionName=" + subscriptionName + ", monthlyPrice="
				+ monthlyPrice + "]";
	}

	@Override
	public double calculateFirstMonth() {
		return BASIC_MONTHLY_PRICE + ENROLLMENT;
	}

	@Override
	public double calculateMonthlyPrice() {
		// TODO Auto-generated method stub
		return BASIC_MONTHLY_PRICE;
	}



}

