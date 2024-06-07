package com.gym.tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gym.tfg.model.User;
import com.gym.tfg.service.MainService;

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
	
	@GetMapping("/insertUser")
	public String insertUser(User userToInsert, Model model) {
		System.out.println(userToInsert.toString());
		service.insertNewUser(userToInsert);
		return "index";
	}
}
