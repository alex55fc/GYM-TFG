package com.gym.tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gym.tfg.model.User;
import com.gym.tfg.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
	
	@Autowired
	UserService userService;
	
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
		userService.insertNewUser(userToInsert);
		
		//Store the user in the session
		session.setAttribute("currentuser", userToInsert);
		
		return "main_pages/home";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    if (session != null) {
	        // Invalidate the session and remove the user's information
	        session.invalidate();
	        System.out.println("User has logged out.");

	    }
	    return "redirect:/";

	}
}
