package com.gym.tfg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gym.tfg.model.User;

@Controller
public class MainController {
	
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
}
