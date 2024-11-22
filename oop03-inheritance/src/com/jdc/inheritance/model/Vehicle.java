package com.jdc.inheritance.model;

public class Vehicle {

	private String make;
	private String model;
	public int year = 2010;
	private static int count;
	final int instanceFinal = 10;
	public static final int staticFinal;

	static {
		staticFinal = 10;
	}

	public Vehicle(final String make) {
		final String data = null;
	}

	public Vehicle() {
		System.out.println("Vehicle constructor called.");
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void startEngine() {
		System.out.println("Vehicle engine started.");
	}

	public void stopEngine() {
		System.out.println("Vehicle engine stopped.");
	}

	public Car getSelf() {
		return null;
	}

	public static void countUp() {
		count++;
	}

	public static int getCount() {
		return count;
	}

}