package com.jdc.balance.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.core.payload.BalancePayload;
import com.jdc.balance.core.payload.input.AccountInput;
import com.jdc.balance.core.payload.output.AccountOutput;
import com.jdc.balance.service.entity.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AccountController {
	
	private final AccountService accountService;

	@PostMapping
	public ResponseEntity<AccountOutput> createAccount(
			@Validated @RequestBody AccountInput input, BindingResult result) {
		return new ResponseEntity<AccountOutput>(accountService.save(input), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<AccountOutput> updateAccount(
			@PathVariable Long id,
			@Validated @RequestBody AccountInput input, BindingResult result) {
		return new ResponseEntity<AccountOutput>(accountService.update(id, input), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deleteAccount(@PathVariable Long id) {
		return new ResponseEntity<Boolean>(accountService.delete(id), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public BalancePayload<AccountOutput> searchAccountById(@PathVariable Long id) {
		return BalancePayload.success(accountService.searchById(id));
	}
	
}
