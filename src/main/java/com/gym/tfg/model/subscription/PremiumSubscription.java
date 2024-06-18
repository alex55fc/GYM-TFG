package com.gym.tfg.model.subscription;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Represents a premium subscription in the gym system.
 */
@Entity
@DiscriminatorValue("PREMIUM")
public class PremiumSubscription extends Subscription {

	private static final double PREMIUM_MONTHLY_PRICE = 40.00;

	public PremiumSubscription() {
		super("PremiumSubscription", PREMIUM_MONTHLY_PRICE);
	}
	/**
	 * Constructs a PremiumSubscription object with the specified ID.
	 * This constructor is for special use, as it is intended to create a subscription
	 * with a specific ID instead of using the auto-incremented ID.
	 */
	public PremiumSubscription(int id) {
		super("PremiumSubscription", PREMIUM_MONTHLY_PRICE);
		this.id = id;
	}

	@Override
	public double calculateFirstMonth() {
		return PREMIUM_MONTHLY_PRICE;
	}

	@Override
	public double calculateMonthlyPrice() {
		return PREMIUM_MONTHLY_PRICE;
	}

	@Override
	public String toString() {
		return "PremiumSubscription [id=" + id + ", subscriptionName=" + subscriptionName + ", monthlyPrice="
				+ monthlyPrice + "]";
	}

	

}
