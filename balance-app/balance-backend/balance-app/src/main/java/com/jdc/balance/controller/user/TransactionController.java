package com.jdc.balance.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.core.payload.BalancePayload;
import com.jdc.balance.core.payload.input.TransactionBaseInput;
import com.jdc.balance.core.payload.output.TransactionBaseOutput;
import com.jdc.balance.core.payload.output.TransactionForMonthlyOutput;
import com.jdc.balance.core.payload.param.TransactionParam;
import com.jdc.balance.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("user/transactions")
@RequiredArgsConstructor
public class TransactionController {
	
	private final TransactionService transactionService;
	
	@PostMapping
	public ResponseEntity<TransactionBaseOutput> createTransaction(
			@RequestBody @Validated
			TransactionBaseInput input,
			BindingResult result
		) {
		return new ResponseEntity<TransactionBaseOutput>(
			transactionService.create(input),
			HttpStatus.CREATED
		);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<TransactionBaseOutput> updateTransaction(
			@PathVariable
			Long id,
			@RequestBody @Validated
			TransactionBaseInput input,
			BindingResult result
		) {
		return new ResponseEntity<TransactionBaseOutput>(
			transactionService.update(id, input),
			HttpStatus.OK
		);
	}
	
	@GetMapping
	public BalancePayload<TransactionForMonthlyOutput> searchTransaction(TransactionParam param) {
		return BalancePayload.success(transactionService.search(param));
	}

}
