package com.jdc.lsp;

public class Woodpecker extends Bird implements CanFly {
	
	@Override
	public void eat() {
		System.out.println("Wood pecker eats.");
	}
	
	@Override
	public void fly() {
		System.out.println("Wood pecker flies.");
	}

}
