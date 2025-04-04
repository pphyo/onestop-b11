package com.jdc.balance.security;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jdc.balance.security.exception.JwtTokenExpiredException;
import com.jdc.balance.security.exception.JwtTokenInvalidException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenProvider {
	
	private final BalanceTokenProperties tokenProperties;
	private final SecretKey key = Jwts.SIG.HS512.key().build();
	
	public Authentication parse(String token, TokenType tokenType) {
		log.debug("Token type: {}", tokenType);
		log.debug("Token string: {}", token);
		try {
			if(!StringUtils.hasLength(token)) {
				throw new JwtTokenInvalidException("Invalid Token");
			}
			
			Claims claims = Jwts.parser()
								.verifyWith(key)
								.requireIssuer(tokenProperties.getIssuer())
								.build()
								.parseSignedClaims(token)
								.getPayload();
			
			String type = claims.get("type", String.class);
			
			if(!type.equals(tokenType.name())) {
				throw new JwtTokenInvalidException("Invalid Token type: expected %s, but got %s".formatted(tokenType, type));
			}
			
			String username = claims.getSubject();
			String role = claims.get("role", String.class);
			var authorities = List.of(new SimpleGrantedAuthority(role));
			
			return UsernamePasswordAuthenticationToken.authenticated(username, null, authorities);
						
		} catch(ExpiredJwtException e) {
			throw new JwtTokenExpiredException("Token has expired. Please refresh token!", e);
		} catch(JwtException e) {
			log.error("Token type: {}", tokenType);
			log.error("Token string: {}", token);
			throw new JwtTokenInvalidException(e.getMessage(), e);
		}

	}
	
	public String generate(Authentication authentication, TokenType tokenType) {
		Date issuedAt = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(issuedAt);
		calendar.add(Calendar.MINUTE, tokenType == TokenType.Access ? tokenProperties.getLife().getAccess() : tokenProperties.getLife().getRefresh());
		
		Date expiredAt = calendar.getTime();
		
		String role = authentication.getAuthorities()
							.stream()
							.map(auth -> auth.getAuthority())
							.findFirst()
							.orElse("ROLE_USER");
		
		return Jwts.builder()
					.subject(authentication.getName())
					.issuer(tokenProperties.getIssuer())
					.issuedAt(issuedAt)
					.expiration(expiredAt)
					.claim("type", tokenType.name())
					.claim("role", role)
					.signWith(key)
					.compact();
	}
	
	public enum TokenType {
		Access, Refresh
	}
	
}
