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

@Controller
public class UserGymClassController {

	@Autowired
	UserGymClassService userGymClassService;

	@Autowired
	GymClassService gymClassService;

	@GetMapping("/gymClassesPage")
	public String gymClassesPage(Model model, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentuser");

		List<UserGymClassDto> listUserGymClass = userGymClassService.listAllUserGymClasses(currentUser.getEmail());
		model.addAttribute("listUserGymClass", listUserGymClass);
		return "gym_classes/gym_classes_overview";
	}

	@PostMapping("/joinSelectedGymClass")
	public String joinSelectedGymClass(@RequestParam("gym_class_id") int gymClassId, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentuser");

		if (!gymClassService.isGymClassWithIdExists(gymClassId)) {
			System.out.println("No existe una clase de gimnasio con ese id ");
			return "redirect:/gymClassesPage";
		}
		GymClass gymClass = gymClassService.getGymClass(gymClassId);

		if (currentUser.getSubscription() == null) {
			System.out.println("Necesitas tener una subscripcion para apuntarte a nuevas clases!");
			return "redirect:/gymClassesPage";
		}

		if (userGymClassService.userInGymClass(currentUser.getEmail(), gymClassId)) {
			System.out.println("El usuario ya esta apuntado a esta clase ");
			return "redirect:/gymClassesPage";
		}

		if (!gymClass.isGymClassSpaceAvaliable()) {
			System.out.println("La clase esta llena");
			return "redirect:/gymClassesPage";
		}

		userGymClassService.insertUserGymClass(currentUser.getEmail(), gymClassId);
		gymClassService.incrementGymClassCapacityCurrent(gymClassId);

		System.out.println("Se inserto correctamente una fila en USER_GYMCLASS\n" + "Usuario " + currentUser.getEmail()
				+ " apuntado a clase con Id " + gymClassId);
		System.out.println("Se actualizo la capacidad actual en la Tabla gym_classes con id " + gymClassId);

		session.setAttribute("currentuser", currentUser);

		return "redirect:/gymClassesPage";
	}

	@PostMapping("/removeUserFromSelectedClass")
	public String removeUserFromSelectedClass(@RequestParam("gym_class_id") int gymClassId, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentuser");

		if (!gymClassService.isGymClassWithIdExists(gymClassId)) {
			System.out.println("No existe una clase de gimnasio con ese id ");
			return "redirect:/gymClassesPage";
		}
		GymClass gymClass = gymClassService.getGymClass(gymClassId);

		if (!userGymClassService.userInGymClass(currentUser.getEmail(), gymClassId)) {
			System.out.println("El usuario no esta apuntado en esta clase.");
			return "redirect:/gymClassesPage";
		}
		if (!userGymClassService.deleteUserGymClass(currentUser.getEmail(), gymClassId)) {
			System.out.println("Error al eliminar una row de la tabla USER_GYMCLASS");
			return "redirect:/gymClassesPage";
		}

		userGymClassService.decrementUserGymClass(gymClassId);
		System.out.println("Se elimino correctamente al usuario de la clase del gimnasio");
		System.out.println(
				"Se decremento correctamente la capacidad actual de la clase del gimnasio con Id " + gymClassId);

		session.setAttribute("currentuser", currentUser);

		return "redirect:/gymClassesPage";

	}
}
