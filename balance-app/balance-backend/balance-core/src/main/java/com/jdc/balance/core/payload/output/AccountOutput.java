package com.jdc.balance.core.payload.output;

import java.math.BigDecimal;

import com.jdc.balance.core.model.entity.AccountEntity;

public record AccountOutput(
			Long id,
			String name,
			BigDecimal amount,
			IconOutput icon
		) {
	
	public static AccountOutput from(AccountEntity entity) {
		return new AccountOutput(
					entity.getId(),
					entity.getName(),
					entity.getInitialAmount(),
					IconOutput.from(entity.getIcon())
				);
	}

}
