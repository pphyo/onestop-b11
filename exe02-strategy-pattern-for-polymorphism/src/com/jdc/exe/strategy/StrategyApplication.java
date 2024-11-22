package com.jdc.exe.strategy;

import com.jdc.exe.strategy.payment.*;

public class StrategyApplication {

	public static void main(String[] args) {
		new StrategyApplication().launch();
	}

	void launch() {
		var cart = new ShoppingCart();
		cart.pay(new AyaPay("pphyo", "0948382823"), 10000);
	}
}