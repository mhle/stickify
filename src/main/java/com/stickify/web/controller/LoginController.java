package com.stickify.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles login requests
 * @author michael le
 *
 */
@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String login() {
		return "login/form";
	}	
	
	@RequestMapping("/login/failed")
	public String loginFailed(Model model) {
		model.addAttribute("isBadCredentials", true); 
		return "login/form";
	}
	
	@RequestMapping("/login/denied")
	public String loginDenied(Model model) {
		model.addAttribute("isDenied", true); 
		return "login/form";
	}
}
