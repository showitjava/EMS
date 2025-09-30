package com.ems.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ems.dto.UserDto;
import com.ems.model.User;
import com.ems.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerUser(UserDto userDto) {
		
		User user = new User();
		user.setUsername(userDto.getUsername());
		
		user.setPassword( passwordEncoder.encode( userDto.getPassword()));
		user.setRole(userDto.getRole() !=null? userDto.getRole():"ROLE_USER");
		
		User u =userRepo.save(user);
		
		return u;
		
		
	}
	
	

}
