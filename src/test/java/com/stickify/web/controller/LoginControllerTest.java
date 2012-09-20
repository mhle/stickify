package com.stickify.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

/**
 * Test <code>LoginController</code>
 * @author mle
 *
 */
public class LoginControllerTest {
	
	private LoginController loginController;
	
	@Before
	public void setup() {
		loginController = new LoginController();
		model = new ExtendedModelMap();
	}
	
	private Model model;
	
	@Test
	public void testLogin() {
		String viewName = loginController.login();
		assertEquals("login/form", viewName);
	}
	
	@Test
	public void testLoginFailed() {
		String viewName = loginController.loginFailed(model);
		Boolean isBadCredentials = (Boolean) model.asMap().get("isBadCredentials");
		assertTrue(isBadCredentials);
		assertEquals("login/form", viewName);
	}
	
	@Test
	public void testLoginDenied() {
		String viewName = loginController.loginDenied(model);
		assertEquals("login/form", viewName);
		Boolean isDenied = (Boolean) model.asMap().get("isDenied");
		assertTrue(isDenied);
	}
}
