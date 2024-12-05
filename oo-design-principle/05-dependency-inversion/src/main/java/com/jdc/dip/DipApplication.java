package com.jdc.dip;

public class DipApplication {
	
	public static void main(String[] args) {
		var os = new Window98Machine(new BurmeseKeyboard(), new Monitor());
		os.boot();
	}

}
