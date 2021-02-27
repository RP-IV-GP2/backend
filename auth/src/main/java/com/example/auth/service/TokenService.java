package com.example.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import com.example.auth.model.User;

import java.util.Date;

@Service
public class TokenService {

	private String key = "Secret key";

	private static final long expirationTime = 1800000;

	public String generateToken(User user) {
		return Jwts.builder().setIssuedAt(new Date(System.currentTimeMillis())).setSubject("JWT")
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(SignatureAlgorithm.HS256, key).compact();
	}

	public Claims decodeToken(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}

}