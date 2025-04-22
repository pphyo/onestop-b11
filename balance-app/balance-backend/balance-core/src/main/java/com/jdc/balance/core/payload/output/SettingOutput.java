package com.jdc.balance.core.payload.output;

import com.jdc.balance.core.model.entity.SettingEntity;
import com.jdc.balance.core.model.entity.consts.CurrencyPosition;
import com.jdc.balance.core.model.entity.consts.DecimalPlace;

public record SettingOutput(Long id, DecimalPlace decimalPlace, CurrencyPosition currencyPosition, CurrencyOutput currency) {

	public static SettingOutput from(SettingEntity entity) {
		if(entity == null) {
			return null;
		}
		return new SettingOutput(entity.getId(), entity.getDecimalPlace(), entity.getCurrencyPosition(), CurrencyOutput.from(entity.getCurrency()));
	}

}
