package com.jdc.balance.core.payload.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.jdc.balance.core.model.entity.consts.TransactionType;

public record TransactionForTransferOutput(
		Long id,
		BigDecimal amount,
		String amountWithFormat,
		TransactionType type,
		LocalDateTime issuedAt,
		String note,
		AccountOutput accountFrom,
		AccountOutput accountTo
	) implements TransactionBaseOutput {

}
