package com.jdc.exe.exception.support;

import java.util.Scanner;

public class InputUtils {

	public static final Scanner sc = new Scanner(System.in);
	
	public static String readString(String message) {
		System.out.print(message);
		return sc.nextLine();
	}
	
	public static int readInt(String message) {
		return Integer.parseInt(readString(message));
	}
	
}
