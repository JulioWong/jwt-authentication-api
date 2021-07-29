package com.example.demo.security;

import org.springframework.stereotype.Component;

import com.example.demo.constants.Constants;
import com.example.demo.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class jwtGenerator {

	public String generate(JwtUser jwtUser) {	
		Claims claims = Jwts.claims()
				.setSubject(jwtUser.getUserName());
		
		claims.put(Constants.USER_ID, String.valueOf(jwtUser.getId()));
		claims.put(Constants.ROLE, jwtUser.getRole());
		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS256, Constants.YOUR_SECRET)
				.compact();
	}
}
