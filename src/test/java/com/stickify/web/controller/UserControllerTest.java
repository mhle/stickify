package com.stickify.web.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;

import com.stickify.app.domain.User;
import com.stickify.app.service.RoleService;
import com.stickify.app.service.UserService;

/**
 * Test <code>UserController</code>
 * @author mle
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	
	@Mock
	private UserService userService;
	
	@Mock
	private RoleService roleService;
	
	private UserController userController;
	private Model model;
	private User user;
	
	@Before
	public void setup() {
		userController = new UserController(userService, roleService);
		model = new ExtendedModelMap();
		user = new User("foobar", "foo", "bar", "foo@bar.com", "foobar");
	}
	
	@Test
	public void testListUsers() {
		List<User> foundUsers = new ArrayList<User>();
		
		when(userService.findAll()).thenReturn(foundUsers);
		
		String viewName = userController.listUsers(model);
		List<User> returnedUsers = (List<User>) model.asMap().get("users");
		
		verify(userService).findAll();
		
		assertEquals(foundUsers.size(), returnedUsers.size());
		assertEquals("admin/user/list", viewName);
	}
	
	@Test
	public void testEdit() {
		long userId = 1;
		
		when(userService.findById(userId)).thenReturn(user);
		
		String viewName = userController.edit(userId, model);
		User returnedUser = (User) model.asMap().get("user");
		
		verify(userService).findById(userId);
		
		assertEquals(user.getUsername(), returnedUser.getUsername());
		assertEquals("admin/user/edit", viewName);
	}
	
	@Test
	public void testView() {
		long userId = 1;
		
		when(userService.findById(userId)).thenReturn(user);
		
		String viewName = userController.view(userId, model);
		User returnedUser = (User) model.asMap().get("user");
		
		verify(userService).findById(userId);
		
		assertEquals(user.getUsername(), returnedUser.getUsername());
		assertEquals("admin/user/view", viewName);
	}
	
	@Test
	public void testDelete() {
		long userId = 1;
		String viewName = userController.delete(userId);
		
		verify(userService).delete(userId);
		
		assertEquals("redirect:/admin/user/list", viewName);
	}
	
	@Test
	public void testSave() {
		BindingResult result = new BindException(user, "user");
		SessionStatus status = mock(SessionStatus.class);
		String viewName = userController.save(user, result, status);
		
		verify(userService).save(user);
		verify(status).setComplete();
		
		assertFalse(result.hasErrors());
		assertEquals("redirect:/admin/user/list", viewName);
	}
	
	@Test
	public void testSaveInvalidUser() {
		BindingResult result = new BindException(user, "user");
		result.reject("test");
		SessionStatus status = mock(SessionStatus.class);
		String viewName = userController.save(user, result, status);
		
		assertTrue(result.hasErrors());
		assertEquals("admin/user/edit", viewName);
	}
}
