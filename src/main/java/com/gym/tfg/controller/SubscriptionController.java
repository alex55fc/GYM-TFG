package com.gym.tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gym.tfg.model.User;
import com.gym.tfg.model.subscription.BasicSubscription;
import com.gym.tfg.model.subscription.PremiumSubscription;
import com.gym.tfg.model.subscription.Subscription;
import com.gym.tfg.service.SubscriptionService;
import com.gym.tfg.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SubscriptionController {

	@Autowired
	SubscriptionService subService;

	@Autowired
	UserService userService;
	
	@GetMapping("/updateSubscriptionPage") 
	public String updateSubscriptionPage() {
		return "subscription_update/subscription_update";
	}

	@PostMapping("/updateSubscription")
	public String updateSubscription(@RequestParam("subscriptionType") String subscriptionType, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentuser");

		if(currentUser != null) {
			Subscription newSubscription;
			if ("BasicSubscription".equalsIgnoreCase(subscriptionType)){
				newSubscription = new BasicSubscription();
			}
			else if("PremiumSubscription".equalsIgnoreCase(subscriptionType)) {
				newSubscription = new PremiumSubscription();
			}
			else {
				throw new IllegalArgumentException("Invalid subscription type: " + subscriptionType);
			}
			//Update the variable newSubscription with the saved instance
			newSubscription = subService.saveNewSubscription(newSubscription);
			currentUser.setSubscription(newSubscription);
			userService.updateUserSubscription(currentUser);

			session.setAttribute("currentuser", currentUser);
		}
		return "redirect:/home";
	}

}
