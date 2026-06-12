package com.msedcl.main.employee.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTUtil {

	// This is your secret password for signing JWT
	private final String SECRET = "msedcl-secret-msedcl-secret-msedcl-secret";

	// Convert your string secret -> cryptographic key
	// Using HAC=SHA algorithm -(HS256)
	public Key getSignKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}
	
	/*
	 * public String generateToken(String userName,String role) { return
	 * Jwts.builder() .setSubject(userName) //username
	 * //.setPayload(userName+""+role) .setIssuedAt(new Date()) .setExpiration(new
	 * Date(System.currentTimeMillis() + 36_00_000)) .signWith(getSignKey())
	 * .compact(); }
	 */
	public String extractUserName(String token) {
		return getClaims(token).getSubject();
	}
	
	//step 1 : Parse Token
	//Step 2 : Verify Signature
	//Step 3 :check Expiry
	//Step 4 : Return claims
	private Claims getClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	
}
