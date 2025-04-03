package com.jdc.balance.core.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenInvalidatedException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public JwtTokenInvalidatedException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
