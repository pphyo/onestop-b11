package com.jdc.inheritance.model;

public class Calculator {
	// Overloading
	public static void calculate(int a, int b) {
		System.out.println(a + b);
	}

	public static void calculate(int a, double b) {
		System.out.println(a + b);
	}

	public static void calculate(double a, int b) {
		System.out.println(a + b);
	}

	public static void calculate(double a, double b) {
		System.out.println(a + b);
	}

	public static void calculate(byte b) {
		System.out.println("Calculate byte...");
	}

	public static void calculate(short s) {
		System.out.println("Calculate short...");
	}

	public static void calculate(int i) {
		System.out.println("Calculate int...");
	}

	public static void calculate(long l) {
		System.out.println("Calculate long...");
	}

}