package com.jdc.balance.handler;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jdc.balance.core.exception.BalanceBusinessException;
import com.jdc.balance.core.exception.BalanceValidationException;
import com.jdc.balance.core.payload.BalanceErrorPayload;
import com.jdc.balance.security.exception.JwtTokenExpiredException;
import com.jdc.balance.security.exception.JwtTokenInvalidException;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler
	ResponseEntity<BalanceErrorPayload> handleValidationException(BalanceValidationException e) {
		return handle(BalanceErrorPayload.error("Invalid Input", e.getMessages()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	ResponseEntity<BalanceErrorPayload> handleBusinessException(BalanceBusinessException e) {
		return handle(BalanceErrorPayload.error("Business Error", e.getMessages()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	ResponseEntity<BalanceErrorPayload> handleAccessDeniedException(AccessDeniedException e) {
		return handle(BalanceErrorPayload.error("Access Denied", List.of("You have no permission to perform this operation.")), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler
	ResponseEntity<BalanceErrorPayload> handleAuthenticationException(AuthenticationException e) {
		var error = switch (e) {
			case UsernameNotFoundException ex -> BalanceErrorPayload.error("Email Not Registered", List.of("No user found with this email: %s.".formatted(ex.getMessage())));
			case BadCredentialsException ex -> BalanceErrorPayload.error("Wrong Password", List.of("Password is wrong. Please type valid password."));
			case JwtTokenExpiredException ex -> BalanceErrorPayload.error("Session Expired", List.of("Your session is timed out. Please refresh or sign in to continue."));
			case JwtTokenInvalidException ex -> BalanceErrorPayload.error("Session Invalid", List.of("Your session is invalid. Please sign in to continue."));
			default -> throw new BalanceBusinessException("Unexpected error");
		};
		return handle(error, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler
	ResponseEntity<BalanceErrorPayload> handleInternal(Exception e) {
		e.printStackTrace();
		return handle(BalanceErrorPayload.error("Internal Server Error", List.of(e.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
	private ResponseEntity<BalanceErrorPayload> handle(BalanceErrorPayload payload, HttpStatus status) {
		return ResponseEntity.status(status).body(payload);
	}

}
