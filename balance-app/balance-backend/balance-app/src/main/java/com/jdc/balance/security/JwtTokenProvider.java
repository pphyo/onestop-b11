package com.jdc.balance.security;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jdc.balance.core.exception.JwtTokenExpiredException;
import com.jdc.balance.core.exception.JwtTokenInvalidatedException;
import com.jdc.balance.core.util.BalanceTokenProperties;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenProvider {
	
	private BalanceTokenProperties properties;
	private final SecretKey key = Jwts.SIG.HS512.key().build();
	
	public Authentication parse(String token, TokenType type) {
		log.debug("Token type: {}", type);
		log.debug("Token string: {}", token);

		try {
			
			if(StringUtils.hasLength(token)) {
				var jwt = Jwts.parser()
							.verifyWith(key)
							.requireIssuer(properties.getIssuer())
							.build()
							.parseSignedClaims(token);
				
				var resultType = jwt.getPayload().get("type");
				
				if(null == resultType || !resultType.equals(type.name())) {
					throw new JwtTokenInvalidatedException("Invalid token type!", null);
				}
				
				String username = jwt.getPayload().getSubject();
				String[] roles = jwt.getPayload().get("role").toString().split(",");
				
				var authorities = Arrays.stream(roles)
										.map(SimpleGrantedAuthority::new)
										.toList();
				
				return UsernamePasswordAuthenticationToken.authenticated(username, null, authorities);
			}
						
		} catch(ExpiredJwtException e) {
			throw new JwtTokenExpiredException("Access token expired. Please refresh token!", e);
		} catch(JwtException e) {
			log.error("Token type: {}", type);
			log.error("Token string: {}", token);
			throw new JwtTokenInvalidatedException(e.getMessage(), e);
		}

		return null;

	}
	
	public String generate(Authentication authentication, TokenType type) {
		Date issuedAt = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(issuedAt);
		calendar.add(Calendar.MINUTE, type == 
				TokenType.Access ? properties.getLife().getAccess() : properties.getLife().getRefresh());
		
		Date expiredAt = calendar.getTime();
		var authorities = authentication.getAuthorities()
							.stream()
							.map(a -> a.getAuthority())
							.collect(Collectors.joining(","));
		
		return Jwts.builder()
					.subject(authentication.getName())
					.issuer(properties.getIssuer())
					.issuedAt(issuedAt)
					.expiration(expiredAt)
					.signWith(key)
					.claim("type", type)
					.claim("role", authorities)
					.compact();
	}
	
	public enum TokenType {
		Access, Refresh
	}
	
}
