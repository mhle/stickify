package com.stickify.app.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.stickify.app.domain.Role;
import com.stickify.app.repository.RoleRepository;
import com.stickify.app.service.RoleService;

/**
 * Unit tests for <code>RoleServiceImpl</code>
 * @author mle
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class RoleServiceImplTest {
	
	@Mock
	private RoleRepository roleRepository;
	
	private RoleService roleService;
	private Role role;
	
	@Before
	public void setup() {
		roleService = new RoleServiceImpl(roleRepository);
		role = new Role("foobar");
	}
	
	@Test
	public void testSaveRole() {
		when(roleRepository.save(role)).thenReturn(role);
		Role savedRole = roleService.save(role);
		verify(roleRepository).save(role);
		assertEquals("foobar", savedRole.getName());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSaveRoleWithNullArgument() {
		role = null;
		when(roleRepository.save(role)).thenThrow(IllegalArgumentException.class);
		roleService.save(role);
	}
	
	@Test
	public void testDeleteRole() {
		long id = 1;
		when(roleRepository.findOne(id)).thenReturn(role);
		Role deletedRole = roleService.delete(id);
		verify(roleRepository).findOne(id);
		verify(roleRepository).delete(role);
		assertEquals("foobar", deletedRole.getName());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDeleteRoleWithNullArgument() {
		when(roleRepository.findOne(null)).thenThrow(IllegalArgumentException.class);
		roleService.delete(null);
	}
	
	@Test
	public void testFindAll() {
		List<Role> mockList = new ArrayList<Role>();
		mockList.add(role);
		when(roleRepository.findAll()).thenReturn(mockList);
		List<Role> roles = roleService.findAll();
		verify(roleRepository).findAll();
		assertEquals("foobar", roles.get(0).getName());
	}
	
	@Test
	public void testFindById() {
		long id = 1;
		when(roleRepository.findOne(id)).thenReturn(role);
		Role foundRole = roleService.findById(id);
		verify(roleRepository).findOne(id);
		assertEquals("foobar", foundRole.getName());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindByIdWithNullArgument() {
		when(roleRepository.findOne(null)).thenThrow(IllegalArgumentException.class);
		roleService.findById(null);
	}
	
	@Test
	public void testFindByName() {
		String name = "foobar";
		when(roleRepository.findByName(name)).thenReturn(role);
		Role foundRole = roleService.findByName(name);
		verify(roleRepository).findByName(name);
		assertEquals("foobar", foundRole.getName());
	}
	
	@Test
	public void testFindByNameNotFound() {
		String name = "foobar";
		when(roleRepository.findByName(name)).thenReturn(null);
		Role foundRole = roleService.findByName(name);
		verify(roleRepository).findByName(name);
		assertNull(foundRole);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindNameWithNullArgument() {
		when(roleRepository.findByName(null)).thenThrow(IllegalArgumentException.class);
		roleService.findByName(null);
	}
}
