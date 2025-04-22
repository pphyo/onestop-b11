package com.jdc.balance.core.payload.input;

import java.math.BigDecimal;
import java.util.function.Function;

import com.jdc.balance.core.model.entity.AccountEntity;
import com.jdc.balance.core.model.entity.IconEntity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccountInput(
			@NotBlank(message = "Account name required!")
			String name,
			@NotNull(message = "Amount required!")
			@Min(value = 1, message = "Amount must be greater than zero!")
			BigDecimal amount,
			Long iconId
		) {
	
	public AccountEntity entity(Function<Long, IconEntity> iconMapper) {
		var entity = new AccountEntity();
		entity.setName(name);
		entity.setAmount(amount);
		entity.setIcon(iconMapper.apply(iconId));
		return entity;
	}

}
