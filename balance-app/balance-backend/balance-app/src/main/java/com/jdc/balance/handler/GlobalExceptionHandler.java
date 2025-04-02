package com.jdc.balance.handler;

import java.util.List;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jdc.balance.core.exception.BalanceBusinessException;
import com.jdc.balance.core.exception.BalanceValidationException;

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
	
	private <T> ResponseEntity<T> handle(T message, HttpStatus code) {
		return ResponseEntity.status(code).body(message);
	}
	
}
