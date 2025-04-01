package com.jdc.balance.core.exception;

import java.util.List;

import lombok.Getter;

@Getter
public class BalanceBaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private List<String> messages;
	
	public BalanceBaseException(List<String> messages) {
		this.messages = messages;
	}

}
