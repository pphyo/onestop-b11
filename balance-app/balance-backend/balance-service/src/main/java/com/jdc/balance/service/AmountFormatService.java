package com.jdc.balance.service;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jdc.balance.core.model.entity.consts.CurrencyPosition;
import com.jdc.balance.core.model.entity.consts.DecimalPlace;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AmountFormatService {
	
	private final SettingService settingService;
	
	public String formatAmount(BigDecimal amount) {
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		var setting = settingService.findByUsername(username);
		
		var formattedAmount = getDecimalFormat(setting.decimalPlace()).format(amount);

		if(setting.currencyPosition() == CurrencyPosition.NONE) {
			return formattedAmount;
		}
		
		return setting.currencyPosition() == CurrencyPosition.AFTER ?
					"%s %s".formatted(formattedAmount, convertSign(setting.currency().code())) :
						"%s %s".formatted(convertSign(setting.currency().code()), formattedAmount);
	}
	
	private String convertSign(String code) {
		return switch(code) {
			case "JPY" -> "¥";
			case "USD" -> "$";
			case "GBP" -> "£";
			case "THB" -> "฿";
			case "EUR" -> "€";
			case "KRW" -> "₩";
			case "VND" -> "₫";
			case "MMK" -> "₭";
			case "PHP" -> "₱";
			default -> "";
		};
	}
	
	private DecimalFormat getDecimalFormat(DecimalPlace decimalPlace) {
		String pattern = switch(decimalPlace) {
			case ZERO -> "#,###";
			case ONE -> "#,###.#";
			case TWO -> "#,###.##";
			default -> "#,###.##";
		};
		
		return new DecimalFormat(pattern);
	}

}
