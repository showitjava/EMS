package com.ems.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	
	// header  , payload , secret key
	private final static String SECRET_KEY ="mysecretkeymysecretkeymysecretkeymysecretkey9090";
	private final long EXP_TIME = 1000*60;
	
	public Key getSignKey()
	{
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}
	
	public String generateToken(String username, String role)
	{
		
		return Jwts.builder()
				.setSubject(username)
				.claim("role", role)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+EXP_TIME))
				.signWith(getSignKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String extractUsername(String token)
	{
		return parseClaims(token).getSubject();
	}
	
	public String extractRole(String token)
	{
		
		return parseClaims(token).get("role",String.class);
	}
	
	public boolean validateToken(String token)
	{
		try {
			parseClaims(token);
			return true;
		}
		catch (JwtException e) {
			return false;
		}
		
	}
	
	public Claims parseClaims(String token)
	{
		return  Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	
	

}
