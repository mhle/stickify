package com.stickify.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stickify.app.domain.Role;

/**
 * Modified on: 4 Jun 2012
 * 
 * Repository interface for <code>Role</code> class.
 *
 * @version: 1.0 4 Jun 2012
 * @author (c): Michael Le
 * 
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
	public Role findByName(String name);
}
