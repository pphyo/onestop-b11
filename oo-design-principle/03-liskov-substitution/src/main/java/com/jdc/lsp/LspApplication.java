package com.jdc.lsp;

public class LspApplication {

	public static void main(String[] args) {
		
		var wp = new Woodpecker();
		var parrot = new Parrot();
		var penguin = new Penguin();
		
		wp.eat();
		parrot.eat();
		penguin.eat();
		
		CanFly cf = wp;
		cf.fly();
		
		CanSwim cs = penguin;
		cs.swim();
		
	}

}
