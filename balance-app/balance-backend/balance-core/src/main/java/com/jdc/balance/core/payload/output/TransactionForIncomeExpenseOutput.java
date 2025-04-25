package com.jdc.balance.core.payload.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.jdc.balance.core.model.entity.consts.TransactionType;

public record TransactionForIncomeExpenseOutput(
		Long id,
		BigDecimal amount,
		String amountWithFormat,
		TransactionType type,
		LocalDateTime issuedAt,
		String note,
		AccountOutput account,
		CategoryOutput category
	) implements TransactionBaseOutput {

}
