package com.gym.tfg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	

	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
	
	//redirect home
    @GetMapping("/home")
    public String home() {
        return "main_pages/home";
    }
}
