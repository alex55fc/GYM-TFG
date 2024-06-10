package com.gym.tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gym.tfg.model.User;
import com.gym.tfg.model.subscription.BasicSubscription;
import com.gym.tfg.model.subscription.PremiumSubscription;
import com.gym.tfg.model.subscription.Subscription;
import com.gym.tfg.service.MainService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@Autowired
	MainService service;
	
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
	
	@GetMapping("/loginPage")
	public String loginPage(Model model) {
		model.addAttribute("userx", new User());
		return "auth/login";
	}
	
	@GetMapping("/registerPage")
	public String registerPage(Model model) {
		model.addAttribute("userx", new User());
		return "auth/register";
	}
	
	@PostMapping("/registerPage/insertUser")
	public String insertUser(User userToInsert, HttpSession session) {
		System.out.println(userToInsert.toString());
		service.insertNewUser(userToInsert);
		
		//Almacena el usuario en la sesion
		session.setAttribute("currentuser", userToInsert);
		
		return "main_pages/home";
	}
	
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
			newSubscription = service.saveNewSubscription(newSubscription);
			currentUser.setSubscription(newSubscription);
			service.updateUserSubscription(currentUser);
			
			session.setAttribute("currentuser", currentUser);
		}
		return "redirect:/home";
	}
	
	/*
	 * Redirects simples
	 */
    @GetMapping("/home")
    public String home() {
        return "main_pages/home";
    }
}
