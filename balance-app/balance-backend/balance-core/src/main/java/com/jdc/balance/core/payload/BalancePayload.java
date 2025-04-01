package com.jdc.balance.core.payload;

import java.time.LocalDateTime;

public record BalancePayload<T>(
			boolean success,
			LocalDateTime issuedAt,
			T payload
		) {
	
	public static <T> BalancePayload<T> success(T payload) {
		return new BalancePayload<T>(true, LocalDateTime.now(), payload);
	}
	
	public static <T> BalancePayload<T> error(T payload) {
		return new BalancePayload<T>(false, LocalDateTime.now(), payload);
	}

}
