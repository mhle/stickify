package com.stickify.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	/**
	 * Renders the home page as HTML in thw web browser.
	 * The home page is different based on whether the user is signed in or not.
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		return "home/index";
	}
}
