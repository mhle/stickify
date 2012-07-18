package com.stickify.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Maps top level entry points, e.g. /, /admin etc.
 */
@Controller
public class EntryController {
	
	@RequestMapping("/")
	public String home() {
		return "home/index";
	}
	
	@RequestMapping("/admin")
	public String admin() {
		return "admin/welcome";
	}
	
}
