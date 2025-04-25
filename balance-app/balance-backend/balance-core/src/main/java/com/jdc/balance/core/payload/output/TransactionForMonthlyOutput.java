package com.jdc.balance.core.payload.output;

import java.time.YearMonth;
import java.util.List;

public record TransactionForMonthlyOutput(
		YearMonth month,
		List<TransactionForDailyOutput> dailyTransactions
	) {
	
	public static TransactionForMonthlyOutput from(
			YearMonth month,
			List<TransactionForDailyOutput> dailyTransactions
		) {
		return new TransactionForMonthlyOutput(month, dailyTransactions);
	}

}
