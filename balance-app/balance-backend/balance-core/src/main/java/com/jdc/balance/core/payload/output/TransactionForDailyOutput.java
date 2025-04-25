package com.jdc.balance.core.payload.output;

import java.time.LocalDate;
import java.util.List;

public record TransactionForDailyOutput(
		LocalDate date,
		List<TransactionBaseOutput> transactions
	) {
	
	public static TransactionForDailyOutput from(
			LocalDate date,
			List<TransactionBaseOutput> transactions
		) {
		return new TransactionForDailyOutput(date, transactions);
	}

}
