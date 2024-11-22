package com.jdc.exe.strategy.payment;

public class KPay extends MobilePay {

	public KPay(String username, String phoneNumber) {
		this.username = username;
		this.phoneNumber = phoneNumber;
	}

	@Override
	public void pay(int amount) {
		System.out.println("""
						  %d is paid using KPay.
						  KPay number: %s
						  KPay name: %s"""
						  .formatted(amount, phoneNumber, username)
						  );
	}

}