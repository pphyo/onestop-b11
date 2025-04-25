package com.jdc.balance.core.payload.input;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.jdc.balance.core.model.entity.consts.TransactionType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record TransactionForIncomeExpenseInput(
		@NotNull(message = "Transaction type required.")
		TransactionType type,
		@NotNull(message = "Amount required.")
		@DecimalMin(value = "0.01", message = "Amount must be positive value")
		BigDecimal amount,
		LocalDateTime issuedAt,
		String note,
		@NotNull(message = "Account required.")
		Long account,
		@NotNull(message = "Category required.")
		Long category
	) implements TransactionInputBase {

}
