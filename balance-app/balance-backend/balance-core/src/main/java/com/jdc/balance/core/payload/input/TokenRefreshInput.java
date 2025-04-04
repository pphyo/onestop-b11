package com.jdc.balance.core.payload.input;

import jakarta.validation.constraints.NotBlank;

public record TokenRefreshInput(
			@NotBlank(message = "Please provide a valid token!")
			String token
		) {

}
