package com.jdc.balance.core.payload.input;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LogInInput(
			@NotBlank(message = "Username required!")
			@Email(message = "Invalid email!")
			String username,
			@NotBlank(message = "Password required!")
			String password
		) {
	
	public Authentication authentication() {
		return UsernamePasswordAuthenticationToken.unauthenticated(username, password);
	}

}
