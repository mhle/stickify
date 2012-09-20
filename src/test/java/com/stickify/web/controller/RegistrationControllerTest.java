package com.stickify.web.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.stickify.app.domain.User;
import com.stickify.app.service.UserService;
import com.stickify.app.util.Constants;
import com.stickify.app.util.FlashMessageType;

/**
 * Test <code>RegistrationController</code>
 * @author mle
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {
	
	@Mock
	private UserService userService;
	
	@Mock
	private MessageSource msgSource;
	
	@Mock
	private UsernamePasswordAuthenticationToken token;
	
	private StandardPasswordEncoder encoder;
	private RegistrationController registrationController;
	private User user;
	
	@Before
	public void setup() {
		encoder = new StandardPasswordEncoder();
		registrationController = new RegistrationController(encoder, msgSource, userService);
		user = new User("foobar", "foo", "bar", "foo@bar.com", "foobar");
	}
	
	@Test
	public void testShowForm() {
		String viewName = registrationController.showForm();
		assertEquals("register/form", viewName);
	}
	
	@Test
	public void testSaveForm() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		String confirmPassword = "foobar";
		BindingResult result = new BindException(user, "user");
		RedirectAttributes redirectAttrs = new RedirectAttributesModelMap();
		
		when(userService.findByUsername(user.getUsername())).thenReturn(null);
		String viewName = registrationController.saveForm(request, confirmPassword, user, result, redirectAttrs);
		
		assertTrue(encoder.matches(confirmPassword, user.getPassword()));
		assertTrue(redirectAttrs.getFlashAttributes().containsKey(Constants.flashMessage));
		assertEquals(FlashMessageType.SUCCESS, redirectAttrs.getFlashAttributes().get(Constants.flashMessageType));
		assertFalse(result.hasErrors());
		assertEquals("redirect:/", viewName);
		
		verify(userService).findByUsername(user.getUsername());
		verify(userService).save(user);
	}
	
	@Test
	public void testSaveFormConfirmPasswordDoesNotMatchPassword() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		String confirmPassword = "foobar2";
		BindingResult result = new BindException(user, "user");
		RedirectAttributes redirectAttrs = new RedirectAttributesModelMap();
		
		when(userService.findByUsername(user.getUsername())).thenReturn(null);
		String viewName = registrationController.saveForm(request, confirmPassword, user, result, redirectAttrs);
		
		assertFalse(redirectAttrs.getFlashAttributes().containsKey(Constants.flashMessage));
		assertFalse(redirectAttrs.getFlashAttributes().containsKey(FlashMessageType.SUCCESS));
		assertTrue(result.hasErrors());
		assertEquals("register/form", viewName);
		
		verify(userService).findByUsername(user.getUsername());
	}
	
	@Test
	public void testSaveFormUsernameAlreadyExists() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		String confirmPassword = "foobar";
		BindingResult result = new BindException(user, "user");
		RedirectAttributes redirectAttrs = new RedirectAttributesModelMap();
		User anotherUser = new User();
		
		when(userService.findByUsername(user.getUsername())).thenReturn(anotherUser);
		String viewName = registrationController.saveForm(request, confirmPassword, user, result, redirectAttrs);
		
		assertFalse(redirectAttrs.getFlashAttributes().containsKey(Constants.flashMessage));
		assertFalse(redirectAttrs.getFlashAttributes().containsKey(FlashMessageType.SUCCESS));
		assertTrue(result.hasErrors());
		assertEquals("register/form", viewName);
		
		verify(userService).findByUsername(user.getUsername());
	}
}
