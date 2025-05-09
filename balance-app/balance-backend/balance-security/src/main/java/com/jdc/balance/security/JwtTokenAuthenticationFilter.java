package com.jdc.balance.security;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jdc.balance.security.JwtTokenProvider.TokenType;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
	
	private RequestMatcher requestMatcher;
	private final JwtTokenProvider tokenProvider;
	
	@PostConstruct
	public void construct() {
		requestMatcher = new AntPathRequestMatcher("/auth/**");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String path = request.getRequestURI();
		log.debug("Request URL: {}", path);
		
		if(requestMatcher.matches(request)) {
			log.debug("Skipping authentication for auth endpoint: {}", path);
			filterChain.doFilter(request, response);
			return;
		}
		
		var token = request.getHeader(HttpHeaders.AUTHORIZATION);
		log.debug("Authorization Header: {}", token);
		
		if (StringUtils.hasLength(token)) {
		
			try {
				if(StringUtils.containsWhitespace(token)) {
					token = token.substring(token.lastIndexOf(" ") + 1);
				}
				
				var authentication = tokenProvider.parse(token, TokenType.Access);
				if(null != authentication) {
					SecurityContextHolder.getContext().setAuthentication(authentication);
					log.debug("Authentication set for user: {}", authentication.getName());
				}
			} catch (Exception e) {
				log.error("Failed to parse token: {}", e.getMessage());
				SecurityContextHolder.clearContext();
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
