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
public class UsersController {

	@Autowired
	SubscriptionService subService;

	@Autowired
	UserService userService;

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
		//no deberia darse este caso
		else {
			return "/home";
		}
	}

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

	/*
	 * obtenemos el objeto subscription asociado al currentUser 
	 * creamos el una subscripcion Premium con el id de la subscription del currentUser
	 * cambiamos el objeto subscription del user por el nuevo subscription premium
	 * usar el método JDBC para actualizar la suscripción en la base de datos
	 * con un metodo CRUD actualizamos el usurio en la db
	 * actualizamos las sesion del currentUser
	 */
	@PostMapping("/changeUserSubscriptionToPremium")
	public String changeUserSubscriptionToPremium(@RequestParam("subscriptionType") String subscriptionType, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentuser");

		if(currentUser != null && currentUser.getSubscription() != null) {
			Subscription sub = currentUser.getSubscription();
			Subscription premiumSubscription = new PremiumSubscription(sub.getId());

			currentUser.setSubscription(premiumSubscription);
			subService.upgradeUserSubscriptionToPremium(currentUser);
			userService.updateUser(currentUser);

			session.setAttribute("currentuser", currentUser);
			System.out.println(currentUser.toString());
			System.out.println(currentUser.getSubscription().toString());

		}
		return "redirect:/home";
	}

	/*
	 * obtenemos el objeto subscription asociado al currentUser 
	 * creamos el una subscripcion Basic con el id de la subscription del currentUser
	 * cambiamos el objeto subscription del user por el nuevo subscription basic
	 * usar el método JDBC para actualizar la suscripción en la base de datos
	 * con un metodo CRUD actualizamos el usurio en la db
	 * actualizamos la sesion del currentUser
	 */
	@PostMapping("/changeUserSubscriptionToBasic")
	public String changeUserSubscriptionToBasic(@RequestParam("subscriptionType") String subscriptionType, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentuser");

		if(currentUser != null && currentUser.getSubscription() != null) {
			Subscription sub = currentUser.getSubscription();
			Subscription basicSubscription = new BasicSubscription(sub.getId());

			currentUser.setSubscription(basicSubscription);
			subService.downgradeUserSubscriptionToBasic(currentUser);
			userService.updateUser(currentUser);

			session.setAttribute("currentuser", currentUser);
			System.out.println(currentUser.toString());
			System.out.println(currentUser.getSubscription().toString());
		}
		return "redirect:/home";

	}

	/*
	 * obtenemos el currentUser
	 * en db establecemos null nuestro subscription del usuer 
	 * 
	 * hacecmos null la subscription del objeto 
	 * actualizamos la session, devolvemos home 
	 */
	@PostMapping("/deleteUserSubscription")
	public String deleteUserSubscription(HttpSession session) {
		User currentUser = (User) session.getAttribute("currentuser");

		if(currentUser != null && currentUser.getSubscription() != null) {
			if(userService.deleteUserSusbcription(currentUser)) {
				
				System.out.println("Se elimino la subscripcion del usuario ");
				subService.deleteSubscriptionFromUser(currentUser);
				
				if(subService.findSubscription(currentUser)) {
					System.out.println("Se elimino la subscripcion asociada al usuario ");
					
					currentUser.setSubscription(null);					
					session.setAttribute("currentuser", currentUser);

					System.out.println(currentUser.toString());
				}
				else {
					System.out.println("Error al eliminar la suscripcion asociada al usuario");
				}
			}
			else {
				System.out.println("Error al eliminar la suscripcion del usuario");
			}
			
		}
		return "redirect:/home";

	}


}
