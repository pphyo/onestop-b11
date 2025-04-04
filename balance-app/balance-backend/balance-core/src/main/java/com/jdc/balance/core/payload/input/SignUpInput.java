package com.jdc.balance.core.payload.input;

import java.util.function.Function;

import com.jdc.balance.core.model.entity.UserEntity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpInput(
			@NotBlank(message = "Name required!")
			String name,
			@NotBlank(message = "Email required!")
			@Email(message = "Invalid email!")
			String username,
			@NotBlank(message = "Password required!")
			String password
		) {
	
	public UserEntity entity(Function<String, String> encoder) {
		var entity = new UserEntity();
		entity.setName(name);
		entity.setUsername(username);
		entity.setAdmin(false);
		entity.setPassword(encoder.apply(password));
		return entity;
	}

}
