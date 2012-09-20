package com.stickify.app.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.stickify.app.domain.User;
import com.stickify.app.repository.UserRepository;
import com.stickify.app.service.UserService;

/**
 * Unit tests for <code>UserServiceImpl</code>
 * @author mle
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	
	@Mock
	private UserRepository userRepository;
	
	private UserService userService;
	private User user;
	
	@Before
	public void setup() {
		userService = new UserServiceImpl(userRepository);
		user = new User("foobar", "foo", "bar", "foo@bar.com", "foobar");
	}
	
	@Test
	public void testSaveUser() {
		when(userRepository.save(user)).thenReturn(user);
		User savedUser = userService.save(user);
		verify(userRepository).save(user);
		assertEquals("foobar", savedUser.getUsername());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSaveUserWithNullArgument() {
		user = null;
		when(userRepository.save(user)).thenThrow(IllegalArgumentException.class);
		userService.save(user);
	}
	
	@Test
	public void testDeleteUser() {
		long id = 1;
		when(userRepository.findOne(id)).thenReturn(user);
		User deletedUser = userService.delete(id);
		verify(userRepository).findOne(id);
		verify(userRepository).delete(user);
		assertEquals("foobar", deletedUser.getUsername());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDeleteUserWithNullArgument() {
		when(userRepository.findOne(null)).thenThrow(IllegalArgumentException.class);
		userService.delete(null);
	}
	
	@Test
	public void testFindAll() {
		List<User> mockList = new ArrayList<User>();
		mockList.add(user);
		when(userRepository.findAll()).thenReturn(mockList);
		List<User> users = userService.findAll();
		verify(userRepository).findAll();
		assertEquals("foobar", users.get(0).getUsername());
	}
	
	@Test
	public void testFindById() {
		long id = 1;
		when(userRepository.findOne(id)).thenReturn(user);
		User foundUser = userService.findById(id);
		verify(userRepository).findOne(id);
		assertEquals("foobar", foundUser.getUsername());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindByIdWithNullArgument() {
		when(userRepository.findOne(null)).thenThrow(IllegalArgumentException.class);
		userService.findById(null);
	}
	
	@Test
	public void testFindByUsername() {
		String username = "foobar";
		when(userRepository.findByUsername(username)).thenReturn(user);
		User foundUser = userService.findByUsername(username);
		verify(userRepository).findByUsername(username);
		assertEquals("foobar", foundUser.getUsername());
	}
	
	@Test
	public void testFindByUsernameUserNotFound() {
		String username = "foobar";
		when(userRepository.findByUsername(username)).thenReturn(null);
		User foundUser = userService.findByUsername(username);
		verify(userRepository).findByUsername(username);
		assertNull(foundUser);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindUsernameWithNullArgument() {
		when(userRepository.findByUsername(null)).thenThrow(IllegalArgumentException.class);
		userService.findByUsername(null);
	}
}
