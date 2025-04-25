package com.jdc.balance.core.payload.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.jdc.balance.core.model.entity.consts.TransactionType;

@JsonTypeInfo(use = Id.NAME, include = As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
	@JsonSubTypes.Type(value = TransactionForTransferOutput.class, name = "transfer"),
	@JsonSubTypes.Type(value = TransactionForIncomeExpenseOutput.class, name = "balance")
})
public interface TransactionBaseOutput {
	
	Long id();
	BigDecimal amount();
	String amountWithFormat();
	TransactionType type();
	LocalDateTime issuedAt();
	String note();

}
