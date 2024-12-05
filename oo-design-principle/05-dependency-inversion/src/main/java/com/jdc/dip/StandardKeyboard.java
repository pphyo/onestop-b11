package com.jdc.dip;

public class StandardKeyboard implements Keyboard {
	
	@Override
	public void type(char ch) {
		System.out.println("Typing %s character.".formatted(ch));		
	}


}
