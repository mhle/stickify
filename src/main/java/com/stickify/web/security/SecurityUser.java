package com.stickify.web.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.stickify.app.domain.User;

/**
 * Modified on: 13 Jun 2012
 * 
 * Extends the Spring Security user in order to inject extra user information such as fullname and email
 *
 * @version: 1.0 13 Jun 2012
 * @author (c): Michael Le
 * 
 */
public class SecurityUser extends org.springframework.security.core.userdetails.User {
	
	private static final long serialVersionUID = 1L;	
	private User user;
    
    public SecurityUser(User user, String username, String password,
                    Collection<? extends GrantedAuthority> authorities) {
    	super(username, password, authorities);
        this.user = user;
    }

    public User getUser() {
    	return user;
    }
}
