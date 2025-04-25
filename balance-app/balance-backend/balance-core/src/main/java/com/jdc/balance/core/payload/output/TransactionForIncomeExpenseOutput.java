package com.jdc.balance.core.payload.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Function;

import com.jdc.balance.core.model.entity.TransactionEntity;
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

	public static TransactionBaseOutput from(TransactionEntity entity, Function<BigDecimal, String> formatMapper) {
		return new TransactionForIncomeExpenseOutput(
				entity.getId(),
				entity.getAmount(),
				formatMapper.apply(entity.getAmount()),
				entity.getType(),
				entity.getIssuedAt(),
				entity.getNote(),
				AccountOutput.from(entity.getAccount(), formatMapper),
				CategoryOutput.from(entity.getCategory()));
	}

}
