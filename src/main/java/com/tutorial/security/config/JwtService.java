package com.tutorial.security.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private static final String SECRET_KEY ="3D0F6BEC35C9A8D247E68C402D4C5A93D7A23C41F6B2E5A467F540ECDCB1751B";

	public String extractUsername(String token) {
		// 
		return extractClaim(token, Claims::getSubject);
	}
	
	public String generatorToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}
	public String generateToken(Map<String, Object> extractClaims,UserDetails userDetails) {
		return Jwts
				.builder()
				.setClaims(extractClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
				.signWith(getSigninkey(), SignatureAlgorithm.HS256)
				.compact();
	}
	public boolean isTokenValid(String token,UserDetails userdtDetails) {
		final String username = extractUsername(token);
		return (username.equals(userdtDetails.getUsername()))&&!isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T extractClaim(String token,Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts
				.parser()
				.setSigningKey(getSigninkey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	private Key getSigninkey() {
		byte[] keybytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keybytes);
	}
}
