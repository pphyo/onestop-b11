package com.jdc.balance.core.payload.output;

public record AuthOutput(
			String name,
			String username,
			Boolean admin,
			String accessToken,
			String refreshToken
		) {

}
