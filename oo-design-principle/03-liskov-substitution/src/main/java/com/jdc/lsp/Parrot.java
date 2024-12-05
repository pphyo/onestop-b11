package com.jdc.lsp;

public class Parrot extends Bird implements CanFly {

	@Override
	public void fly() {
		System.out.println("Parrot flies.");		
	}

}
