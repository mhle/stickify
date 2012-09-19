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
 * Service implementation for <code>Role</code>
 *
 * @version: 1.0 4 Jun 2012
 * @author (c): Michael Le
 * 
 */
@Service
public class RoleServiceImpl implements RoleService {
	
	private RoleRepository roleRepository;
	
	@Inject
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Role save(Role role) {
		return roleRepository.save(role);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Role delete(Long roleId) {
		Role deleted = roleRepository.findOne(roleId); 
		roleRepository.delete(deleted);
		return deleted;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Role findById(Long id) {
		return roleRepository.findOne(id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}
}
