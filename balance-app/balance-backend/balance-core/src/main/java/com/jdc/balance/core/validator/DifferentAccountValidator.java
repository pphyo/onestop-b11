package com.jdc.balance.core.validator;

import com.jdc.balance.core.payload.input.TransactionForTransferInput;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DifferentAccountValidator
	implements ConstraintValidator<DifferentAccount, TransactionForTransferInput> {

	@Override
	public boolean isValid(TransactionForTransferInput input, ConstraintValidatorContext context) {
		if(input.accountFrom() == null || input.accountTo() == null) {
			return true;
		}
		return !input.accountFrom().equals(input.accountTo());
	}

	
	
}
