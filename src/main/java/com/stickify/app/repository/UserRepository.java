package com.stickify.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stickify.app.domain.User;

/**
 * Modified on: 31 May 2012
 * 
 * Provides CRUD operations on User objects
 * Scanned by Spring Data JPA for bean creation
 * @author Michael Le
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);
}