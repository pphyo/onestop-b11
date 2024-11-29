package com.jdc.core;

import java.util.Scanner;

public class InputUtil {
	
	public static final Scanner SC = new Scanner(System.in);
	
	public static String getString(String message) {
		System.out.print(message);
		return SC.nextLine();
	}
	
	public static int getInt(String message) {
		return Integer.parseInt(getString(message));
	}

}
