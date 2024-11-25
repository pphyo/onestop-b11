package com.jdc.exe.strategy;

import com.jdc.exe.strategy.payment.PaymentStrategy;

public class ShoppingCart {

	public void buy(PaymentStrategy strategy, int amount) {
		strategy.pay(amount);
	}

}