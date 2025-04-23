package com.jdc.balance.core.payload.input;

import java.math.BigDecimal;
import java.util.function.Function;

import com.jdc.balance.core.model.entity.AccountEntity;
import com.jdc.balance.core.model.entity.IconEntity;
import com.jdc.balance.core.model.entity.UserEntity;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccountInput(
			@NotBlank(message = "Account name required!")
			String name,
			@NotNull(message = "Amount required!")
			@DecimalMin(value = "0.01", message = "Amount must be positive!")
			BigDecimal amount,
			@NotNull(message = "Icon required!")
			Long icon
		) {
	
	public AccountEntity entity(Function<Long, IconEntity> iconMapper, UserEntity user) {
		var entity = new AccountEntity();
		entity.setName(name);
		entity.setAmount(amount);
		entity.setIcon(iconMapper.apply(icon));
		entity.setUser(user);
		return entity;
	}

}
