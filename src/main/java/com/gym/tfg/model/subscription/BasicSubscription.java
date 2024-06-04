package com.gym.tfg.model.subscription;

public class BasicSubscription extends Subscription {

	private static final double ENROLLMENT = 20.00;
	private static final double BASIC_MONTHLY_PRICE = 20.00;

	public BasicSubscription(String subscriptionName, double monthlyPrice) {
		super("BasicSubscription", BASIC_MONTHLY_PRICE);
		// TODO Auto-generated constructor stub
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

