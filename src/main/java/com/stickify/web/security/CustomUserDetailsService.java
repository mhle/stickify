package com.stickify.web.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stickify.app.domain.Role;
import com.stickify.app.domain.User;
import com.stickify.app.service.UserService;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Inject
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User domainUser = userService.findByUsername(username); 
		
		if (domainUser == null) {
			throw new UsernameNotFoundException("Could not find user: " + username);
		}
		
		return new SecurityUser(
				domainUser,
				domainUser.getUsername(), 
				domainUser.getPassword(),
				getGrantedAuthorities(domainUser.getRoles()));		
	}

	/**
     * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
     * @param roles {@link String} of roles
     * @return list of granted authorities
     */
    public static List<GrantedAuthority> getGrantedAuthorities(Set<Role> roles) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            for( Role role : roles ) {
            	authorities.add( new SimpleGrantedAuthority( role.getName() ));
            }
            return authorities;
    }
	
	
}
