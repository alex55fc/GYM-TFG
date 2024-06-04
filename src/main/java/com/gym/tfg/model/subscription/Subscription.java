package com.gym.tfg.model.subscription;

public abstract class Subscription {
	
	protected String subscriptionName;
	protected double monthlyPrice;

	
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
	
	public abstract double calculateFirstMonth();
	public abstract double calculateMonthlyPrice();
	
}
