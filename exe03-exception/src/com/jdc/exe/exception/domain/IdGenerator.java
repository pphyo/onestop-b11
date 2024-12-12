package com.jdc.exe.exception.domain;

public class IdGenerator {
	
	private static int counter = 0;
	
	public static int next() {
		return ++ counter;
	}

}
