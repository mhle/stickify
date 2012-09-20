package com.stickify.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.stickify.app.domain.User;
import com.stickify.app.service.UserService;
import com.stickify.app.util.Constants;
import com.stickify.app.util.FlashMessageType;
import com.stickify.web.security.SecurityUser;


/**
 * Test <code>AccountController</code>
 * @author mle
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {
	
	@Mock
	private UserService userService;
	
	@Mock
	private MessageSource msgSource;
	
	@Mock
	private UsernamePasswordAuthenticationToken token;
	
	private StandardPasswordEncoder encoder;
	private AccountController accountController;
	private Model model;
	private User user;
	
	@Before
	public void setup() {
		encoder = new StandardPasswordEncoder();
		model = new ExtendedModelMap();
		accountController = new AccountController(userService, msgSource, encoder);
		user = new User("foobar", "foo", "bar", "foo@bar.com", "foobar");
	}
	
	@Test
	public void testShowDetails() {
		SecurityUser secUser = mock(SecurityUser.class);
		
		when(token.getPrincipal()).thenReturn(secUser);
		when(secUser.getUser()).thenReturn(user);
		String viewName = accountController.showDetails(model, token);
		
		verify(token).getPrincipal();
		verify(secUser).getUser();
		
		assertTrue(model.containsAttribute("user"));
		User returnedUser = (User) model.asMap().get("user");
		assertEquals("account/view", viewName);
		assertEquals("foobar", returnedUser.getUsername());
	}
	
	@Test
	public void testChangePassword() {
		SecurityUser secUser = mock(SecurityUser.class);
		
		when(token.getPrincipal()).thenReturn(secUser);
		when(secUser.getUser()).thenReturn(user);
		String viewName = accountController.showChangePasswordForm(model, token);
		
		verify(token).getPrincipal();
		verify(secUser).getUser();
		
		assertTrue(model.containsAttribute("user"));
		User returnedUser = (User) model.asMap().get("user");
		assertEquals("account/changepassword", viewName);
		assertEquals("foobar", returnedUser.getUsername());
	}
	
	@Test
	public void testSavePassword() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		String confirmPassword = "foobar";
		BindingResult result = new BindException(user, "user");
		RedirectAttributes redirectAttrs = new RedirectAttributesModelMap();
		SessionStatus status = mock(SessionStatus.class);
		
		String viewName = accountController.savePassword(request, confirmPassword, user, result, redirectAttrs, status);
		assertEquals("redirect:/account/details", viewName);
		assertTrue(encoder.matches(confirmPassword, user.getPassword()));
		assertFalse(result.hasErrors());
		
		assertTrue(redirectAttrs.getFlashAttributes().containsKey(Constants.flashMessage));
		assertEquals(FlashMessageType.SUCCESS, redirectAttrs.getFlashAttributes().get(Constants.flashMessageType));
			
		verify(userService).save(user);
		verify(status).setComplete();
	}
	
	@Test
	public void testSavePasswordWithNoMatchingConfirmPassword() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		String confirmPassword = "foobar2";
		BindingResult result = new BindException(user, "user");
		RedirectAttributes redirectAttrs = new RedirectAttributesModelMap();
		SessionStatus status = mock(SessionStatus.class);
		
		String viewName = accountController.savePassword(request, confirmPassword, user, result, redirectAttrs, status);
		assertEquals("account/changepassword", viewName);
		assertTrue(result.hasErrors());
	}
	
	@Test
	public void testshowEditForm() {
		SecurityUser secUser = mock(SecurityUser.class);
		
		when(token.getPrincipal()).thenReturn(secUser);
		when(secUser.getUser()).thenReturn(user);
		String viewName = accountController.showEditForm(model, token);
		
		verify(token).getPrincipal();
		verify(secUser).getUser();
		
		assertTrue(model.containsAttribute("user"));
		User returnedUser = (User) model.asMap().get("user");
		assertEquals("account/edit", viewName);
		assertEquals("foobar", returnedUser.getUsername());
	}
	
	@Test
	public void testSaveUsernameNotChanged() {
		BindingResult result = new BindException(user, "user");
		SessionStatus status = mock(SessionStatus.class);
		Principal principal = mock(Principal.class);
		
		String viewName = accountController.save(user, result, status, principal);
		assertEquals("redirect:/account/details", viewName);
		assertFalse(result.hasErrors());
		
		verify(userService).save(user);
		verify(status).setComplete();
	}
	
	@Test
	public void testSaveUsernameChangedToValidUsername() {
		BindingResult result = new BindException(user, "user");
		SessionStatus status = mock(SessionStatus.class);
		Principal principal = mock(Principal.class);
		
		user.setUsername("validUsername");
		when(userService.findByUsername(user.getUsername())).thenReturn(null);
		String viewName = accountController.save(user, result, status, principal);
		
		verify(userService).findByUsername(user.getUsername());
		verify(userService).save(user);
		verify(status).setComplete();
		
		assertEquals("redirect:/account/details", viewName);
		assertFalse(result.hasErrors());
	}
	
	@Test
	public void testSaveUsernameChangedToInvalidUsername() {
		BindingResult result = new BindException(user, "user");
		SessionStatus status = mock(SessionStatus.class);
		Principal principal = mock(Principal.class);
		User anotherUser = new User();
		
		user.setUsername("inValidUsername");
		when(userService.findByUsername(user.getUsername())).thenReturn(anotherUser);
		String viewName = accountController.save(user, result, status, principal);
		
		verify(userService).findByUsername(user.getUsername());

		assertTrue(result.hasErrors());
		assertEquals("account/edit", viewName);
	}
}
