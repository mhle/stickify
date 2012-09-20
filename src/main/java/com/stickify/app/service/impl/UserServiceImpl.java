package com.stickify.app.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stickify.app.domain.User;
import com.stickify.app.repository.UserRepository;
import com.stickify.app.service.UserService;

/**
 * Modified on: 4 Jun 2012
 * 
 * Service implementation for <code>User</code>.
 *
 * @version: 1.0 4 Jun 2012
 * @author (c): Michael Le
 * 
 */
@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserRepository userRepository;

	@Inject
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public User save(User user) {
        return userRepository.save(user);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public User delete(Long userId) {
		LOGGER.debug("Deleting user with id: " + userId);
        
        User deleted = userRepository.findOne(userId);
               
        userRepository.delete(deleted);
        return deleted;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findById(Long id) {
		return userRepository.findOne(id);
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}	
}
