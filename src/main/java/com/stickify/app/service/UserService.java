package com.stickify.app.service;

import java.util.List;

import com.stickify.app.domain.User;

/**
 * Modified on: 4 Jun 2012
 * 
 * Service interface for <code>User</code>.
 *
 * @version: 1.0 4 Jun 2012
 * @author (c): Michael Le
 * 
 */
public interface UserService {
	/**
     * Save or update a user.
     * @param User The information of the user
     * @return  The created user.
     */
    public User save(User user);

    /**
     * Deletes a user.
     * @param userId  The id of the deleted user.
     * @return  The deleted user.
     */
    public User delete(Long userId);

    /**
     * Finds all users.
     * @return  A list of users.
     */
    public List<User> findAll();

    /**
     * Finds user by id.
     * @param id The id of the wanted user.
     * @return The found user. If no user is found, this method returns null.
     */
    public User findById(Long id);
    
    /**
     * Finds user by username.
     * @param username The username of the wanted user.
     * @return The found user. If no user is found, this method returns null.
     */
    public User findByUsername(String username);
}
