package com.gym.tfg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gym.tfg.model.User;
import com.gym.tfg.model.dto.UserGymClassDto;
import com.gym.tfg.model.gymclasses.GymClass;
import com.gym.tfg.service.GymClassService;
import com.gym.tfg.service.UserGymClassService;

import jakarta.servlet.http.HttpSession;

/**
 * UserGymClassController handles user interactions related to gym classes,
 */
@Controller
public class UserGymClassController {

	@Autowired
	UserGymClassService userGymClassService;

	@Autowired
	GymClassService gymClassService;

	/** 
     * Displays the gym classes page with the list of classes the current user is enrolled in.
	 */
	@GetMapping("/gymClassesPage")
	public String gymClassesPage(Model model, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentuser");

		List<UserGymClassDto> listUserGymClass = userGymClassService.listAllUserGymClasses(currentUser.getEmail());
		model.addAttribute("listUserGymClass", listUserGymClass);
		return "gym_classes/gym_classes_overview";
	}

	/**
	 * Allows the current user to join a selected gym class.
	 * 
	 * Checks if a gym class exists with the provided ID.
     * Checks if the user has an active subscription.
     * Checks if the user is already enrolled in the gym class.
     * Checks if there is available space in the gym class.
	 */
	@PostMapping("/joinSelectedGymClass")
	public String joinSelectedGymClass(@RequestParam("gym_class_id") int gymClassId, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentuser");

		if (!gymClassService.isGymClassWithIdExists(gymClassId)) {
			System.out.println("No gym class exists with that ID");
			return "redirect:/gymClassesPage";
		}
		GymClass gymClass = gymClassService.getGymClass(gymClassId);

		if (currentUser.getSubscription() == null) {
			System.out.println("You need a subscription to join new classes!");
			return "redirect:/gymClassesPage";
		}

		if (userGymClassService.userInGymClass(currentUser.getEmail(), gymClassId)) {
			System.out.println("You are already enrolled in this class");
			return "redirect:/gymClassesPage";
		}

		if (!gymClass.isGymClassSpaceAvaliable()) {
			System.out.println("The class is full");
			return "redirect:/gymClassesPage";
		}
		GymClass selectedGymClass = gymClassService.getGymClass(gymClassId);
		userGymClassService.insertUserGymClass(currentUser.getEmail(), selectedGymClass.getId(), selectedGymClass.getGymClassName(), selectedGymClass.getWeeklyDay());
		gymClassService.incrementGymClassCapacityCurrent(gymClassId);

		System.out.println("Successfully inserted a row into USER_GYMCLASS\\nUser " + currentUser.getEmail()
				+ " enrolled in class with ID " + gymClassId);
		System.out.println("Updated the current capacity in the gym_classes table with ID " + gymClassId);

		session.setAttribute("currentuser", currentUser);

		return "redirect:/gymClassesPage";
	}
	
	/**
     * Allows the current user to leave a selected gym class.

	 * Checks if a gym class exists with the provided ID.
     * Checks if the user is already enrolled in the gym class.
     * Checks if there was an error deleting a row.
	 */
	@PostMapping("/removeUserFromSelectedClass")
	public String removeUserFromSelectedClass(@RequestParam("gym_class_id") int gymClassId, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentuser");

		if (!gymClassService.isGymClassWithIdExists(gymClassId)) {
			System.out.println("No gym class exists with that ID");
			return "redirect:/gymClassesPage";
		}
		GymClass gymClass = gymClassService.getGymClass(gymClassId);

		if (!userGymClassService.userInGymClass(currentUser.getEmail(), gymClassId)) {
			System.out.println("You are not enrolled in this class.");
			return "redirect:/gymClassesPage";
		}
		if (!userGymClassService.deleteUserGymClass(currentUser.getEmail(), gymClassId)) {
			System.out.println("Error deleting a row from the USER_GYMCLASS table");
			return "redirect:/gymClassesPage";
		}

		userGymClassService.decrementUserGymClass(gymClassId);
		
		System.out.println("Successfully removed the user from the gym class");
		System.out.println("Successfully decremented the current capacity of the gym class with ID " + gymClassId);

		session.setAttribute("currentuser", currentUser);

		return "redirect:/gymClassesPage";

	}
}
