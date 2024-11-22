package com.jdc.exe.strategy.payment;

public abstract class CreditCard implements PaymentStrategy {
	protected String cardNumber;
	protected String cvcNumber;
	protected String holderName;
}