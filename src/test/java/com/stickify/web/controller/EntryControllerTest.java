package com.stickify.web.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.security.Principal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

/**
 * Test <code>EntryController</code>
 * @author mle
 *
 */
public class EntryControllerTest {
	
	private EntryController entryController;
	
	@Mock
	private UsernamePasswordAuthenticationToken token;
	
	@Before
	public void setup() {
		entryController = new EntryController();
	}
	
	@Test
	public void testHome() {
		Model model = new ExtendedModelMap();
		String viewName = entryController.home(model, token);
		assertEquals("home/index", viewName);
	}
	
	@Test
	public void testAdmin() {
		String viewName = entryController.admin();
		assertEquals("admin/welcome", viewName);
	}
}
