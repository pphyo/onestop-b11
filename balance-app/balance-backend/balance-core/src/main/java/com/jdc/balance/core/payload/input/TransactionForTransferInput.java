package com.jdc.balance.core.payload.input;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Function;

import com.jdc.balance.core.model.entity.AccountEntity;
import com.jdc.balance.core.model.entity.TransactionEntity;
import com.jdc.balance.core.model.entity.consts.TransactionType;
import com.jdc.balance.core.validator.DifferentAccount;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@DifferentAccount
public record TransactionForTransferInput(
		@NotNull(message = "Transaction type required.")
		TransactionType type,
		@NotNull(message = "Amount required.")
		@DecimalMin(value = "0.01", message = "Amount must be positive value")
		BigDecimal amount,
		LocalDateTime issuedAt,
		String note,
		@NotNull(message = "From account required.")
		Long accountFrom,
		@NotNull(message = "To account required.")
		Long accountTo
	) implements TransactionInputBase {
	
	public TransactionEntity entity(Function<Long, AccountEntity> accountMapper) {
		var entity = new TransactionEntity();
		entity.setAmount(amount);
		entity.setType(type);
		entity.setIssuedAt(issuedAt == null ? LocalDateTime.now() : issuedAt);
		entity.setNote(note);
		
		var from = accountMapper.apply(accountFrom);
		var to = accountMapper.apply(accountTo);
		entity.transfer(from, to, amount);
		
		entity.setAccount(from);
		entity.setTargetAccount(to);
		
		return entity;
	}

}
