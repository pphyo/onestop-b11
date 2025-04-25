package com.jdc.balance.core.payload.input;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.jdc.balance.core.model.entity.consts.TransactionType;

public interface TransactionInputBase {
	
	TransactionType type();
	BigDecimal amount();
	LocalDateTime issuedAt();
	String note();

}
