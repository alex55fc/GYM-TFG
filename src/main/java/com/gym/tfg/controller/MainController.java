package com.gym.tfg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	

	@GetMapping("/")
	public String indexPage(HttpSession session ) {
		if(session != null) {
			session.invalidate();
		}
		return "index";
	}
	
	//redirect home
    @GetMapping("/home")
    public String home() {
        return "main_pages/home";
    }
    
}
