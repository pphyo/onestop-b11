package com.jdc.exe.exception.support;

public class StringUtils {
	
	public static boolean isEmpty(String str) {
		return null == str || str.isBlank();
	}
	
	public static void showMessage(String text) {
		String line = "";
		
		for(int i = 0, l = text.length(); i < l; i ++) {
			line = line.concat("*");
		}
		
		System.out.println("**".concat(line).concat("**"));
		System.out.println("* ".concat(text).concat(" *"));
		System.out.println("**".concat(line).concat("**"));
	}

}
