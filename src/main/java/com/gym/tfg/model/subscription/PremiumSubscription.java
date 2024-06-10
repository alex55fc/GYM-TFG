package com.gym.tfg.model.subscription;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PREMIUM")
public class PremiumSubscription extends Subscription {

	private static final double PREMIUM_MONTHLY_PRICE = 40.00;

	public PremiumSubscription() {
		super("PremiumSubscription", PREMIUM_MONTHLY_PRICE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calculateFirstMonth() {
		// TODO Auto-generated method stub
		return PREMIUM_MONTHLY_PRICE;
	}

	@Override
	public double calculateMonthlyPrice() {
		// TODO Auto-generated method stub
		return PREMIUM_MONTHLY_PRICE;
	}

	

}
