package com.jdc.balance.core.payload.input;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Function;

import com.jdc.balance.core.model.entity.AccountEntity;
import com.jdc.balance.core.model.entity.CategoryEntity;
import com.jdc.balance.core.model.entity.TransactionEntity;
import com.jdc.balance.core.model.entity.consts.TransactionType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record TransactionForIncomeExpenseInput(
		@NotNull(message = "Transaction type required.")
		TransactionType type,
		@NotNull(message = "Amount required.")
		@DecimalMin(value = "0.01", message = "Amount must be positive value")
		BigDecimal amount,
		LocalDateTime issuedAt,
		String note,
		@NotNull(message = "Account required.")
		Long account,
		@NotNull(message = "Category required.")
		Long category
	) implements TransactionBaseInput {

	public TransactionEntity entity(Function<Long, AccountEntity> accountMapper, Function<Long, CategoryEntity> categoryMapper) {
		var entity = new TransactionEntity();
		entity.setAmount(amount);
		entity.setType(type);
		entity.setIssuedAt(issuedAt == null ? LocalDateTime.now() : issuedAt);
		entity.setNote(note);
		
		var accountEntity = accountMapper.apply(account);
		accountEntity.balanceAmount(type, amount);
		
		entity.setAccount(accountEntity);
		entity.setCategory(categoryMapper.apply(category));
		return entity;
	}

}
