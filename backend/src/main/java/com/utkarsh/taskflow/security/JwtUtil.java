package com.utkarsh.taskflow.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")
	private String secretKeyString;
	
	@Value("${jwt.expiration}")
	private long expiration;
	
	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(secretKeyString.getBytes());
	}
	
	public String generateToken(String email) {
		return Jwts.builder()
			.subject(email)
			.issuedAt(new Date())
			.expiration(new Date(System.currentTimeMillis() + expiration))
			.signWith(getSigningKey())
			.compact();
	}
	
	public String extractEmail(String token) {
		return Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}
	
	public boolean isTokenValid(String token) {
		try {
			Jwts.parser()
			.verifyWith(getSigningKey())
			.build()
			.parseSignedClaims(token);
		return true;
		} catch(Exception e) {
			return false;
		}
	}
}
