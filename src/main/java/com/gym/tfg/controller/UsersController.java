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

/**
 * UsersController handles user-related operations such as managing subscriptions.
 */
@Controller
public class UsersController {

	@Autowired
	SubscriptionService subService;

	@Autowired
	UserService userService;

	/**
     * Redirects the user to the appropriate subscription page based on their current subscription.
	 */
	@GetMapping("/userSubscriptionPage") 
	public String redirectUserSusbcriptionPage(HttpSession session) {
		User currentUser = (User) session.getAttribute("currentuser");
		if(currentUser.getSubscription() == null) {
			return "subscriptions/create_user_subscription";

		}
		else if(currentUser.getSubscription().getSubscriptionName().equalsIgnoreCase("BasicSubscription")) {
			return "subscriptions/update_basic_subscription";
		}
		else if(currentUser.getSubscription().getSubscriptionName().equalsIgnoreCase("PremiumSubscription")) {
			return "subscriptions/update_premium_subscription";
		}
		else {
			return "/home";
		}
	}

	/**
	 * Creates a new user subscription based on the provided subscription type.
	 * @param subscriptionType The type of subscription to create
	 */
	@PostMapping("/createUserSusbcription")
	public String createUserSusbcription(@RequestParam("subscriptionType") String subscriptionType, HttpSession session) {
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
			userService.createUserSubscription(currentUser);

			session.setAttribute("currentuser", currentUser);
		}
		return "redirect:/home";
	}

	/**
	 * Changes the current user's subscription to Premium.
	 */
	@PostMapping("/changeUserSubscriptionToPremium")
	public String changeUserSubscriptionToPremium(@RequestParam("subscriptionType") String subscriptionType, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentuser");

		if(currentUser != null && currentUser.getSubscription() != null) {
			// Retrieve the subscription object associated with the currentUser.
			Subscription sub = currentUser.getSubscription();
			// Create a Premium subscription using the currentUser's subscription ID.
			Subscription premiumSubscription = new PremiumSubscription(sub.getId());

			currentUser.setSubscription(premiumSubscription);
			subService.upgradeUserSubscriptionToPremium(currentUser);
			userService.updateUser(currentUser);
			
			//Update the currentUser's session.
			session.setAttribute("currentuser", currentUser);
			System.out.println(currentUser.toString());
			System.out.println(currentUser.getSubscription().toString());

		}
		return "redirect:/home";
	}

	/**
	 * Changes the current user's subscription to Basic.
	 */
	@PostMapping("/changeUserSubscriptionToBasic")
	public String changeUserSubscriptionToBasic(@RequestParam("subscriptionType") String subscriptionType, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentuser");

		if(currentUser != null && currentUser.getSubscription() != null) {
			// Retrieve the subscription object associated with the currentUser.
			Subscription sub = currentUser.getSubscription();
			// Create a Basic subscription using the currentUser's subscription ID.
			Subscription basicSubscription = new BasicSubscription(sub.getId());

			currentUser.setSubscription(basicSubscription);
			subService.downgradeUserSubscriptionToBasic(currentUser);
			userService.updateUser(currentUser);

			//Update the currentUser's session.
			session.setAttribute("currentuser", currentUser);
			System.out.println(currentUser.toString());
			System.out.println(currentUser.getSubscription().toString());
		}
		return "redirect:/home";

	}

	/**
     * Deletes the user's subscription.
	 */
	@PostMapping("/deleteUserSubscription")
	public String deleteUserSubscription(HttpSession session) {
		User currentUser = (User) session.getAttribute("currentuser");

		if(currentUser != null && currentUser.getSubscription() != null) {
			if(userService.deleteUserSusbcription(currentUser)) {
				
				System.out.println("User's subscription deleted");
				subService.deleteSubscriptionFromUser(currentUser);
				
				if(subService.findSubscription(currentUser)) {
					System.out.println("Subscription associated with the user deleted.");
					
					currentUser.setSubscription(null);					
					session.setAttribute("currentuser", currentUser);

					System.out.println(currentUser.toString());
				}
				else {
					System.out.println("Error deleting the subscription associated with the user.");
				}
			}
			else {
				System.out.println("Error deleting the user's subscription.");
			}
			
		}
		return "redirect:/home";

	}


}
