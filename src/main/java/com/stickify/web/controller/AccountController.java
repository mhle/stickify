package com.stickify.web.controller;

import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stickify.app.domain.User;
import com.stickify.app.service.UserService;
import com.stickify.app.util.Constants;
import com.stickify.app.util.FlashMessageType;
import com.stickify.web.security.SecurityUser;

/**
 * Handles methods related to account actions
 * @author mle
 *
 */
@Controller
@RequestMapping("/account")
@SessionAttributes("user")
public class AccountController {
	
	Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	/**
	 * The user service
	 */
	@Inject
	private UserService userService;
	
	/**
	 * The message source
	 */
	@Inject
	private MessageSource msgSource;
	
	/**
	 * The password encoder
	 */
	@Inject
	private StandardPasswordEncoder encoder;
	
	/**
	 * Handles request for showing a user's details
	 * @param model
	 * @param token
	 * @return viewname
	 */
	@RequestMapping("/details")
	public String showDetails(Model model, UsernamePasswordAuthenticationToken token) {
		model.addAttribute("user", ((SecurityUser) token.getPrincipal()).getUser());
		return "account/view";
	}
	
	/**
	 * Handles request for changing password
	 * @param model
	 * @param token
	 * @return viewname
	 */
	@RequestMapping("/changepassword")
	public String showChangePasswordForm(Model model, UsernamePasswordAuthenticationToken token) {
		model.addAttribute("user", ((SecurityUser) token.getPrincipal()).getUser());
		return "account/changepassword";
	}
	
	/**
	 * Handles form submission for changing password
	 * @param request
	 * @param confirmPassword
	 * @param user
	 * @param result
	 * @param redirectAttrs
	 * @param status
	 * @return viewname
	 */
	@RequestMapping(value="/savepassword", method=RequestMethod.POST)
	public String savePassword(HttpServletRequest request, @RequestParam("confirmPassword") String confirmPassword, 
			@Valid User user, BindingResult result, RedirectAttributes redirectAttrs, SessionStatus status) {
		if (!confirmPassword.equals(user.getPassword())) {
			result.reject("user.form.error.confirmPassword", "Passwords do not match");
		}
		
		if (result.hasErrors()) {
			return "account/changepassword";
		} 
		
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		redirectAttrs.addFlashAttribute(Constants.flashMessage, msgSource.getMessage("flashmsg.saveNewPasswordSuccess", null, request.getLocale()));
		redirectAttrs.addFlashAttribute(Constants.flashMessageType, FlashMessageType.SUCCESS);
		userService.save(user);
		status.setComplete();
		return "redirect:/account/details";
	}
	
	/**
	 * Handles request to edit account details
	 * @param model
	 * @param token
	 * @return viewname
	 */
	@RequestMapping("/edit")
	public String showEditForm(Model model, UsernamePasswordAuthenticationToken token) {
		model.addAttribute("user", ((SecurityUser) token.getPrincipal()).getUser());
		return "account/edit";
	}
	
	/**
	 * Handles form submission for editing account details
	 * @param user
	 * @param result
	 * @param status
	 * @param principal
	 * @return viewname
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(@Valid User user, BindingResult result, SessionStatus status, Principal principal) {
		
		if (!user.getUsername().equals(principal.getName())) {
			//username has changed
			User foundUser = userService.findByUsername(user.getUsername());
			if (foundUser != null) {
				result.reject("user.form.error.usernameExists", "Username already exists");
			}
		}
		
		if (result.hasErrors()) {
			return "account/edit";
		} 
		
		userService.save(user);
		status.setComplete();
		return "redirect:/account/details";
	}
}
