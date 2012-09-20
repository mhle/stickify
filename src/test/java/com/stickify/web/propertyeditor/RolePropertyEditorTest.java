package com.stickify.web.propertyeditor;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.stickify.app.domain.User;
import com.stickify.app.service.RoleService;

/**
 * Test <code>RolePropertyEditor</code>
 * @author mle
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class RolePropertyEditorTest {
	
	@Mock
	private RoleService roleService;
	
	private RolePropertyEditor rolePropertyEditor;
	
	@Before
	public void setup() {
		rolePropertyEditor = new RolePropertyEditor(roleService);
	}
	
	@Test
	public void testSetAsText() {
		User foundUser = new User();
		foundUser.setId(1L);
		String text = "1";
		rolePropertyEditor.setAsText(text);
		
		verify(roleService).findById(new Long(text));
		
		assertEquals(new Long(text), foundUser.getId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetAsTextThrowsIllegalArgument() {
		String text = "foobar";
		rolePropertyEditor.setAsText(text);
	}
}
