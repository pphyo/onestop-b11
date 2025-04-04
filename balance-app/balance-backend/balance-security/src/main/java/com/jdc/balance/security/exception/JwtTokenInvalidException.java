package com.jdc.balance.security.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenInvalidException extends AuthenticationException {

	private static final long serialVersionUID = 1L;
	
	public JwtTokenInvalidException(String msg) {
		super(msg);
	}

	public JwtTokenInvalidException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
