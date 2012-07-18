package com.stickify.web.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.stickify.app.domain.Role;
import com.stickify.app.domain.User;
import com.stickify.app.service.RoleService;
import com.stickify.app.service.UserService;
import com.stickify.web.propertyeditor.RolePropertyEditor;

/**
 * Maps user related entry points
 * @author mle
 *
 */
@Controller
@SessionAttributes("user")
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	private UserService userService;
	
	@Inject
	private RoleService roleService;
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Role.class, new RolePropertyEditor(roleService));	       
	}
	
	@ModelAttribute("allRoles")
	public List<Role> allRoles() {
		return roleService.findAll();
	}
	
	@RequestMapping("/admin/user/list")
	public String listUsers(Model model) {
		model.addAttribute("users", userService.findAll());
		return "admin/user/list";
	}
	
	@RequestMapping(value="/admin/user/{userId}/edit", method=RequestMethod.GET)
	public String edit(@PathVariable Long userId, Model model) {
		model.addAttribute("user", userService.findById(userId)); 
		return "admin/user/edit";
	}
	
	@RequestMapping(value="/admin/user/save", method=RequestMethod.POST) 
	public String save(@Valid User user, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return "admin/user/edit";
		} 
		
		userService.save(user);
		status.setComplete();
		return "redirect:/admin/user/list";
	}
	
	@RequestMapping("/admin/user/{userId}/delete")
	public String delete(@PathVariable Long userId) {
		userService.delete(userId);
		return "redirect:/admin/user/list";
	}
	
	@RequestMapping("/admin/user/{userId}/view")
	public String view(@PathVariable Long userId, Model model) {
		model.addAttribute("user", userService.findById(userId));
		return "admin/user/view";
	}
	
	
}
