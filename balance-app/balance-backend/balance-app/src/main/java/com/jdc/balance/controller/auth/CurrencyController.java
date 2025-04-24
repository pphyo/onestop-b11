package com.jdc.balance.controller.auth;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.core.payload.BalancePayload;
import com.jdc.balance.core.payload.output.CurrencyOutput;
import com.jdc.balance.core.payload.param.CurrencyParam;
import com.jdc.balance.service.CurrencyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("any/currencies")
@RequiredArgsConstructor
public class CurrencyController {
	
	private final CurrencyService currencyService;
	
	@GetMapping("{id}")
	public BalancePayload<CurrencyOutput> searchCurrencyById(@PathVariable Long id) {
		return BalancePayload.success(currencyService.searchById(id));
	}
	
	@GetMapping
	public BalancePayload<List<CurrencyOutput>> searchCurrency(
				CurrencyParam param
			) {
		return BalancePayload.success(currencyService.search(param));
	}

}
