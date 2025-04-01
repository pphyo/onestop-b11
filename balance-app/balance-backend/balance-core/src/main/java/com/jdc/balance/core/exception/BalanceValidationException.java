package com.jdc.balance.core.exception;

import java.util.List;

public class BalanceValidationException extends BalanceBaseException {
	
	private static final long serialVersionUID = 1L;

	public BalanceValidationException(List<String> messages) {
		super(messages);
	}

}
