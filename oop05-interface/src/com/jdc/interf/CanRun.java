package com.jdc.interf;

public interface CanRun {
	
	int i = 10;
	
	// abstract
	void run();
	
	// jdk8
	default void start() {
		System.out.println("default(instance) start() runs.");
	}
	
	// jdk8
	static void init() {
		System.out.println("static init() runs.");
	}
	
	// jdk9
	@SuppressWarnings("unused")
	private void instacePrivate() {}
	@SuppressWarnings("unused")
	private static void staticPrivate() {}
	
}