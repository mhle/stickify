package com.stickify.web.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stickify.app.domain.User;
import com.stickify.app.service.UserService;
import com.stickify.app.util.Constants;
import com.stickify.app.util.FlashMessageType;

/**
 * Registration entry points
 * @author mle
 *
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@Inject
	private StandardPasswordEncoder encoder;
	
	@Inject
	private MessageSource msgSource;
	
	@Inject
	private UserService userService;
	
	@ModelAttribute
	public User user() {
		return new User();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm() {
		return "register/form";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveForm(HttpServletRequest request, @RequestParam("confirmPassword") String confirmPassword, @Valid User user, BindingResult result, RedirectAttributes redirectAttrs) {
		if (!confirmPassword.equals(user.getPassword())) {
			result.reject("user.form.error.confirmPassword", "Passwords do not match");
		}
		
		if (userService.findByUsername(user.getUsername()) != null) {
			result.reject("user.form.error.usernameExists", "Username already exists");
		}
		
		if (result.hasErrors()) {
			return "register/form";
		} 
		
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userService.save(user);
		redirectAttrs.addFlashAttribute(Constants.flashMessage, msgSource.getMessage("user.register.success.message", new String[] {user.getFirstName()}, request.getLocale()));
		redirectAttrs.addFlashAttribute(Constants.flashMessageType, FlashMessageType.SUCCESS); 
		return "redirect:/";
	}
}
