package com.jdc.balance.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jdc.balance.core.exception.BalanceBusinessException;
import com.jdc.balance.core.exception.BalanceValidationException;
import com.jdc.balance.security.exception.JwtTokenExpiredException;
import com.jdc.balance.security.exception.JwtTokenInvalidException;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	private final String MAP_KEY = "error";
	private static final Map<Class<? extends AuthenticationException>, String> errors = new HashMap<>();
	
	static {
		errors.put(BadCredentialsException.class, "Wrong password!");
		errors.put(DisabledException.class, "Account is temporarily disabled.Please contact admin!");
	}
	
	@ExceptionHandler
	ResponseEntity<Map<String, List<String>>> handle(BalanceValidationException e) {
		return handle(Map.of(MAP_KEY, e.getMessages()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	ResponseEntity<Map<String, List<String>>> handle(BalanceBusinessException e) {
		return handle(Map.of(MAP_KEY, e.getMessages()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	ResponseEntity<Map<String, List<String>>> handle(AuthenticationException e) {
		String result = switch(e) {
			case BadCredentialsException exp -> errors.get(BadCredentialsException.class);
			case DisabledException exp -> errors.get(DisabledException.class);
			default -> e.getMessage();
		};

		return handle(Map.of(MAP_KEY, List.of(result)), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler
	ResponseEntity<Map<String, List<String>>> handle(AccessDeniedException e) {
		return handle(Map.of(MAP_KEY, List.of(e instanceof AccessDeniedException ? "You don't have authority to access this." : e.getMessage())), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler
	ResponseEntity<Map<String, List<String>>> handle(JwtTokenExpiredException e) {
		return handle(Map.of(MAP_KEY, List.of(e.getMessage())), HttpStatus.REQUEST_TIMEOUT);
	}
	
	@ExceptionHandler
	ResponseEntity<Map<String, List<String>>> handle(JwtTokenInvalidException e) {
		return handle(Map.of(MAP_KEY, List.of(e.getMessage())), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler
	ResponseEntity<Map<String, List<String>>> handle(Exception e) {
		e.printStackTrace();
		return handle(Map.of(MAP_KEY, List.of(e.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
	private <T> ResponseEntity<T> handle(T message, HttpStatus code) {
		return ResponseEntity.status(code).body(message);
	}
	
}
