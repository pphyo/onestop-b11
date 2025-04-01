package com.jdc.balance.service.entity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.core.payload.input.CurrencyInput;
import com.jdc.balance.core.payload.output.CurrencyOutput;
import com.jdc.balance.repository.entity.CurrencyRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CurrencyService {
	
	private final CurrencyRepository currencyRepo;

	public CurrencyOutput create(CurrencyInput input) {
		return CurrencyOutput.from(currencyRepo.save(input.entity()));
	}
	
}













