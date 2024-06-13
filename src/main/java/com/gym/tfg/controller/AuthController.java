package com.gym.tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gym.tfg.model.User;
import com.gym.tfg.service.UserService;
import com.gym.tfg.util.Constants;

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
	public String insertUser(User userToInsert, HttpSession session, Model model) {
		userParamsToLowerCase(userToInsert);
		cleanUserSpaces(userToInsert);
		
		// Verify email is repeated
		if(userService.isUserWithEmailExists(userToInsert.getEmail())) {
			System.out.println("User with this email in db");
			model.addAttribute("userx", userToInsert); 
			return "auth/register";
		}
		//Verify that the password is valid
		if(!passwordIsValid(userToInsert.getPassword())) {
			System.out.println(" 1. The password must not have leading or trailing whitespace.\r\n"
					+ " 2. The password must not contain spaces.\r\n"
					+ " 3. The password must contain at least one uppercase letter.");
			model.addAttribute("userx", userToInsert); 
			return "auth/register";
		}
		// Verify minimun age is correct
		if(userToInsert.getAge() < Constants.MINIMUN_AGE || userToInsert.getAge() > Constants.MAXIMUN_AGE) {
			System.out.println("Not avaliable minimun age is "+ Constants.MINIMUN_AGE + ", and maximun is "+ Constants.MAXIMUN_AGE);
			model.addAttribute("userx", userToInsert); 
			return "auth/register";
		}
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
	
	private void userParamsToLowerCase(User user) {
		user.setEmail(user.getEmail().toLowerCase());
		user.setName(user.getName().toLowerCase());
		user.setSurname(user.getSurname().toLowerCase());	
		
	}
    private void cleanUserSpaces(User user) {
    	
        String email = user.getEmail().trim();
        while (email.contains("  ")) {
            email = email.replace("  ", " ");
        }
        user.setEmail(email);

        String name = user.getName().trim();
        while (name.contains("  ")) {
            name = name.replace("  ", " ");
        }
        user.setName(name);

        String surname = user.getSurname().trim();
        while (surname.contains("  ")) {
            surname = surname.replace("  ", " ");
        }
        user.setSurname(surname);
    }
    /**
     * Validates a password based on the following criteria:
     * 1. The password must not have leading or trailing whitespace.
     * 2. The password must not contain spaces.
     * 3. The password must contain at least one uppercase letter.
     *
     * @param password the password to be validated
     * @return true if the password meets all the criteria, false in otherwise
     */
    private boolean passwordIsValid(String password) {
        if (!password.trim().equals(password)) {
            return false;
        }

        if (password.contains(" ")) {
            return false;
        }

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }

        return false; 
    }
}
