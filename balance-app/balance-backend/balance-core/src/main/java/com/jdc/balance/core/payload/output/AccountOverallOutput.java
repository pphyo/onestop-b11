package com.jdc.balance.core.payload.output;

import java.math.BigDecimal;
import java.util.function.Function;

public record AccountOverallOutput(
		String incomeWithFormat,
		String expenseWithFormat,
		String balanceWithFormat
	) {
	
	public static AccountOverallOutput from(Function<BigDecimal, String> formatMapper, BigDecimal income, BigDecimal expense, BigDecimal balance) {
		return new AccountOverallOutput(
					formatMapper.apply(income),
					formatMapper.apply(expense),
					formatMapper.apply(balance));
	}

}
