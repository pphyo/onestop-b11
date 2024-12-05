package com.jdc.dip;

public class Window98Machine {
	
	private Keyboard keyboard;
	private Monitor monitor;
	
	public Window98Machine(Keyboard keyboard, Monitor monitor) {
		this.keyboard = keyboard;
		this.monitor = monitor;
	}
	
	public void boot() {
		System.out.println("Boot os");
		
		System.out.println("Preparing keyboard: ");
		switch(keyboard) {
			case StandardKeyboard sk -> sk.type('a');
			case BurmeseKeyboard bk -> bk.type('\u1000');
			case JapaneseKeyboard jk -> jk.type('\u3040');
			default -> throw new IllegalArgumentException("Not supported keyboard!");
		}
		
		System.out.println("Preparing mointor: " + monitor);
	}

}






