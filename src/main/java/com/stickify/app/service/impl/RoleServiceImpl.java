package com.stickify.app.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stickify.app.domain.Role;
import com.stickify.app.repository.RoleRepository;
import com.stickify.app.service.RoleService;

/**
 * Modified on: 4 Jun 2012
 * 
 * <code>Role</code> service implementation.
 *
 * @version: 1.0 4 Jun 2012
 * @author (c): Michael Le
 * 
 */
@Service
@Transactional(readOnly=true)
public class RoleServiceImpl implements RoleService {
	
	@Inject
	private RoleRepository roleRepository;
	
	@Override
	@Transactional
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	@Transactional
	public Role delete(Long roleId) {
		Role deleted = roleRepository.findOne(roleId); 
		roleRepository.delete(deleted);
		return deleted;
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role findById(Long id) {
		return roleRepository.findOne(id);
	}
	
	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}
}
