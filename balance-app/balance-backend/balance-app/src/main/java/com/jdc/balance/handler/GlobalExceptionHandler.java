package com.jdc.balance.handler;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jdc.balance.core.exception.BalanceBusinessException;
import com.jdc.balance.core.exception.BalanceValidationException;
import com.jdc.balance.core.exception.JwtTokenExpiredException;
import com.jdc.balance.core.exception.JwtTokenInvalidatedException;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	private final String MAP_KEY = "error";
	
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
		return handle(Map.of(MAP_KEY, List.of(e.getMessage())), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler
	ResponseEntity<Map<String, List<String>>> handle(AccessDeniedException e) {
		return handle(Map.of(MAP_KEY, List.of(e.getMessage())), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler
	ResponseEntity<Map<String, List<String>>> handle(JwtTokenExpiredException e) {
		return handle(Map.of(MAP_KEY, List.of(e.getMessage())), HttpStatus.REQUEST_TIMEOUT);
	}
	
	@ExceptionHandler
	ResponseEntity<Map<String, List<String>>> handle(JwtTokenInvalidatedException e) {
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
