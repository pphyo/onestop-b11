package com.jdc.balance.core.payload;

import java.time.LocalDateTime;
import java.util.List;

public record BalanceErrorPayload(
		LocalDateTime issuedAt,
		String title,
		List<String> messages
	) {

public static BalanceErrorPayload error(String title, List<String> messages) {
	return new BalanceErrorPayload(LocalDateTime.now(), title, messages);
}

}