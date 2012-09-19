package com.stickify.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Maps top level entry points, e.g. /, /admin etc.
 */
@Controller
public class EntryController {
	
	/**
	 * Handles request for home screen
	 * @return viewname
	 */
	@RequestMapping("/")
	public String home() {
		return "home/index";
	}
	
	/**
	 * Handles requests for admin pages
	 * @return
	 */
	@RequestMapping("/admin")
	public String admin() {
		return "admin/welcome";
	}
	
}
