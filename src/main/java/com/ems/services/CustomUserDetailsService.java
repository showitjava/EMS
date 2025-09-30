package com.ems.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ems.model.User;
import com.ems.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	
	private UserRepository userRepo;
	CustomUserDetailsService(UserRepository userRepo){
		
		this.userRepo =userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	
		User user = userRepo.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException("User name not found" +username));
		
		
		return org.springframework.security.core.userdetails.User
				.withUsername(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRole().replace("ROLE_", "")).build();
		
		// ROLE_ADMIN
		//ADMIN
		
	}

}
