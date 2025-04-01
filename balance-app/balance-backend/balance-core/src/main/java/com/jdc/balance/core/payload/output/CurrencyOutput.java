package com.jdc.balance.core.payload.output;

import com.jdc.balance.core.model.entity.CurrencyEntity;

public record CurrencyOutput(
			Long id,
			String name,
			String code
		) {

	public static CurrencyOutput from(CurrencyEntity entity) {
		return new CurrencyOutput(entity.getId(), entity.getName(), entity.getCode());
	}

}
