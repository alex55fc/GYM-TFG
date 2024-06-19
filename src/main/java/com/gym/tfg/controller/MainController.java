package com.gym.tfg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * MainController handles requests for the main pages of the application.
 */
@Controller
public class MainController {
	
	/**
	 * Displays the index page.
	 */
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
	
	/**
	 * Redirects to the home page
	 */
    @GetMapping("/home")
    public String home() {
        return "main_pages/home";
    }
    
    /**
     * Redirect to the trainers page
     */
    @GetMapping("/trainers")
    public String trainers() {
    	return "main_pages/trainers";
    }
    
    /**
     * Redirect to the trainers page
     */
    @GetMapping("/gymClassesView")
    public String gymClassesView() {
    	return "main_pages/gym_classes_view";
    }
    
}
