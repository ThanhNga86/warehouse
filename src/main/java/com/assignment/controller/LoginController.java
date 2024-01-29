package com.assignment.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class LoginController {

	@GetMapping("/login")
	public String Login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/dashboard";
		} else {
			return "account/login/login";
			//return "redirect:/dashboard";
		}
		
	}
	@GetMapping("/doipass")
		public String passEmail() {
			
		return "account/login/doipass";
		}
	
	
}
