package com.jdc.balance.core.payload.input;

import java.util.function.Function;

import com.jdc.balance.core.model.entity.CurrencyEntity;
import com.jdc.balance.core.model.entity.SettingEntity;
import com.jdc.balance.core.model.entity.UserEntity;
import com.jdc.balance.core.model.entity.consts.CurrencyPosition;
import com.jdc.balance.core.model.entity.consts.DecimalPlace;

import jakarta.validation.constraints.NotNull;

public record SettingInput(
		@NotNull(message = "Decimal place required.")
		DecimalPlace decimalPlace,
		@NotNull(message = "Currency position required.")
		CurrencyPosition currencyPosition,
		@NotNull(message = "Please select currency.")
		Long currency) {

	public SettingEntity entity(
			Function<Long, CurrencyEntity> currencyMapper,
			UserEntity user) {
		var entity = new SettingEntity();
		entity.setDecimalPlace(decimalPlace);
		entity.setCurrencyPosition(currencyPosition);
		entity.setCurrency(currencyMapper.apply(currency));
		entity.setUser(user);
		return entity;
	}

}
