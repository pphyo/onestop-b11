package com.jdc.exe.strategy;

import com.jdc.exe.strategy.payment.*;

public class StrategyApplication {

	public static void main(String[] args) {
		new StrategyApplication().launch();
	}

	void launch() {
		var cart = new ShoppingCart();
		cart.buy(new VisaCard("10293949430303", "012", "Pyae Phyo"), 10000);
	}
}