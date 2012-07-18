package com.stickify.app.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.stickify.app.domain.Role;

/**
 * Modified on: 4 Jun 2012
 * 
 * Service interface for <code>Role</code>.
 *
 * @version: 1.0 4 Jun 2012
 * @author (c): Michael Le
 * 
 */
public interface RoleService {
	/**
     * Save or update an <code>Role</code>.
     * @param Role The information of the <code>Role</code>
     * @return  The created <code>Role</code>.
     */
    public Role save(Role role);

    /**
     * Deletes an <code>Role</code>.
     * @param roleId  The id of the deleted <code>Role</code>.
     * @return  The deleted <code>Role</code>.
     */
    public Role delete(Long roleId);

    /**
     * Finds all <code>Role</code>s.
     * @return  A list of <code>Role</code>s.
     */
    @PreAuthorize("hasRole('ADMIN')")
    public List<Role> findAll();

    /**
     * Finds <code>Role</code> by id.
     * @param id The id of the wanted <code>Role</code>.
     * @return The found <code>Role</code>. If no <code>Role</code> is found, this method returns null.
     */
    public Role findById(Long id);
    
    /**
     * Finds <code>Role</code> by name.
     * @param name The name of the wanted <code>Role</code>.
     * @return The found <code>Role</code>. If no <code>Role</code> is found, this method returns null.
     */
    public Role findByName(String name);
}
