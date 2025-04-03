package com.jdc.balance.core.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenExpiredException extends AuthenticationException {

	private static final long serialVersionUID = 1L;
	
	public JwtTokenExpiredException(String msg) {
		super(msg);
	}

	public JwtTokenExpiredException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
