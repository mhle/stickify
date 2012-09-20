package com.stickify.web.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test <code>EntryController</code>
 * @author mle
 *
 */
public class EntryControllerTest {
	
	private EntryController entryController;
	
	@Before
	public void setup() {
		entryController = new EntryController();
	}
	
	@Test
	public void testHome() {
		String viewName = entryController.home();
		assertEquals("home/index", viewName);
	}
	
	@Test
	public void testAdmin() {
		String viewName = entryController.admin();
		assertEquals("admin/welcome", viewName);
	}
}
