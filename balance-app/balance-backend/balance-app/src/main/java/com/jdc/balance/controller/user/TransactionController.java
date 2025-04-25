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
import com.jdc.balance.core.payload.input.TransactionForIncomeExpenseInput;
import com.jdc.balance.core.payload.input.TransactionForTransferInput;
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
	public ResponseEntity<TransactionBaseOutput> createIncomeExpense(
			@RequestBody @Validated
			TransactionForIncomeExpenseInput input,
			BindingResult result
		) {
		return new ResponseEntity<TransactionBaseOutput>(
			transactionService.create(input),
			HttpStatus.CREATED
		);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<TransactionBaseOutput> updateIncomeExpense(
			@PathVariable
			Long id,
			@RequestBody @Validated
			TransactionForIncomeExpenseInput input,
			BindingResult result
		) {
		return new ResponseEntity<TransactionBaseOutput>(
			transactionService.update(id, input),
			HttpStatus.OK
		);
	}
	
	@PostMapping("transfer")
	public ResponseEntity<TransactionBaseOutput> createTransfer(
			@RequestBody @Validated
			TransactionForTransferInput input,
			BindingResult result
		) {
		return new ResponseEntity<TransactionBaseOutput>(
			transactionService.createTransfer(input),
			HttpStatus.CREATED
		);
	}
	
	@PutMapping("transfer/{id}")
	public ResponseEntity<TransactionBaseOutput> updateTransfer(
			@PathVariable
			Long id,
			@RequestBody @Validated
			TransactionForTransferInput input,
			BindingResult result
		) {
		return new ResponseEntity<TransactionBaseOutput>(
			transactionService.updateTransfer(id, input),
			HttpStatus.OK
		);
	}
	
	@GetMapping
	public BalancePayload<TransactionForMonthlyOutput> searchTransaction(TransactionParam param) {
		return BalancePayload.success(transactionService.search(param));
	}

}
