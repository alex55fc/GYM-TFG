package com.gym.tfg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

/**
 * MainController handles requests for the main pages of the application.
 */
@Controller
public class MainController {
	
	/**
	 * Displays the index page and invalidates the session if it exists.
	 */
	@GetMapping("/")
	public String indexPage(HttpSession session ) {
		if(session != null) {
			session.invalidate();
		}
		return "index";
	}
	
	/**
	 * Redirects to the home page
	 */
    @GetMapping("/home")
    public String home() {
        return "main_pages/home";
    }
    
}
