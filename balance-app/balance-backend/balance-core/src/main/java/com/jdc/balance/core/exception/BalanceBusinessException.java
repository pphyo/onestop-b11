package com.jdc.balance.core.exception;

import java.util.List;

public class BalanceBusinessException extends BalanceBaseException {

	private static final long serialVersionUID = 1L;
	
	public BalanceBusinessException(String message) {
		this(List.of(message));
	}

	public BalanceBusinessException(List<String> messages) {
		super(messages);
	}

}
