package com.jdc.balance.core.payload.input;

import com.jdc.balance.core.model.entity.CurrencyEntity;

import jakarta.validation.constraints.NotBlank;

public record CurrencyInput(
		@NotBlank(message = "Currency name required!")
		String name,
		@NotBlank(message = "Currency code required!")
		String code
		) {

	public CurrencyEntity entity() {
		var currencyEntity = new CurrencyEntity();
		currencyEntity.setName(name);
		currencyEntity.setCode(code);
		return currencyEntity;
	}
	
}
