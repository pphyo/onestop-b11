package com.jdc.balance.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.core.payload.input.CurrencyInput;
import com.jdc.balance.core.payload.output.CurrencyOutput;
import com.jdc.balance.service.entity.CurrencyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("currencies")
public class CurrencyController {
	
	private final CurrencyService currencyService;
	
	@PostMapping
	public ResponseEntity<CurrencyOutput> createCurrency(
			@Validated @RequestBody CurrencyInput input, BindingResult result) {
		return new ResponseEntity<CurrencyOutput>(
				currencyService.create(input), 
				HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public void updateCurrency() {
		
	}

}














