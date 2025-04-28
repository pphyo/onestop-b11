package com.jdc.balance.core.payload.input;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.jdc.balance.core.model.entity.consts.TransactionType;

@JsonTypeInfo(
	use = Id.NAME, include = As.EXTERNAL_PROPERTY, property = "type", visible = true
)
@JsonSubTypes({
	@JsonSubTypes.Type(value = TransactionForIncomeExpenseInput.class, name = "Income"),
	@JsonSubTypes.Type(value = TransactionForIncomeExpenseInput.class, name = "Expense"),
	@JsonSubTypes.Type(value = TransactionForTransferInput.class, name = "Transfer")
})
public interface TransactionBaseInput {
	
	TransactionType type();
	BigDecimal amount();
	LocalDateTime issuedAt();
	String note();

}
