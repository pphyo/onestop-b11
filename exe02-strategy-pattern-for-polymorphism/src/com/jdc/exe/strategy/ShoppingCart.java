package com.jdc.exe.strategy;

import com.jdc.exe.strategy.payment.PaymentStrategy;

public class ShoppingCart {

	public void pay(PaymentStrategy strategy, int amount) {
		strategy.pay(amount);
	}

}