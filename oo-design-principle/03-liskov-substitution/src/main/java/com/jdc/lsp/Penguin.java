package com.jdc.lsp;

public class Penguin extends Bird implements CanSwim {
	
	@Override
	public void swim() {
		System.out.println("Peguin swims.");
	}

}
