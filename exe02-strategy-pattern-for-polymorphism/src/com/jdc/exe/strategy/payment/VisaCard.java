package com.jdc.exe.strategy.payment;

public class VisaCard extends CreditCard {

	public VisaCard(String cardNumber, String cvcNumber, String holderName) {
		this.cardNumber = cardNumber;
		this.cvcNumber = cvcNumber;
		this.holderName = holderName;
	}

	@Override
	public void pay(int amount) {
		System.out.println("""
						   %d is paid using Visa Card.
						   Holder name: %s
						   Card number: %s"""
						   .formatted(amount, holderName, cardNumber)
						  );
	}

}