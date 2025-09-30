package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.dto.LoginRequest;
import com.ems.dto.LoginResponse;
import com.ems.dto.UserDto;
import com.ems.model.User;
import com.ems.security.JwtUtil;
import com.ems.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private JwtUtil jwtUitl;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping("/auth/register")
	public ResponseEntity<User> registerUser(@RequestBody UserDto userDto)
	{
		User user =userService.registerUser(userDto);
		
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}
	
	@PostMapping("/auth/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest)
	{
		Authentication auth  = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		UserDetails user  = (UserDetails) auth.getPrincipal();
		
		String role =user.getAuthorities().iterator().next().getAuthority();
		
		
		String token =jwtUitl.generateToken(user.getUsername(),role);
		return ResponseEntity.ok(new LoginResponse(token));
	}

}
