package com.jdc.balance.core.payload.output;

import java.math.BigDecimal;
import java.util.function.Function;

import com.jdc.balance.core.model.entity.AccountEntity;

public record AccountOutput(
		Long id,
		String name,
		BigDecimal amount,
		String amountWithFormat,
		IconOutput icon) {

	public static AccountOutput from(AccountEntity entity, Function<BigDecimal, String> amountFormatMapper) {
		return new AccountOutput(
				entity.getId(),
				entity.getName(),
				entity.getAmount(),
				amountFormatMapper.apply(entity.getAmount()),
				IconOutput.from(entity.getIcon()));
	}

}

