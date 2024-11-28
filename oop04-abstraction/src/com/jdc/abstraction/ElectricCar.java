package com.jdc.abstraction;

public class ElectricCar extends Car {

	private int batteryCapacity;

	public ElectricCar(String model, String color, int year, int door, int batteryCapacity) {
		super(model, color, year, door);
		this.batteryCapacity = batteryCapacity;
	}

	public int getBatteryCapacity() {
		return batteryCapacity;
	}

	public void startEngine() {
		System.out.println("Electric car engine started.");
	}

	public void stopEngine() {
		System.out.println("Electric car engine stopped.");
	}

	@Override
	public String toString() {
		return """
			Model: %s
			Color: %s
			Year : %d
			Door : %d
			Battery: %d""".formatted(getModel(), getColor(), getYear(), getDoor(), getBatteryCapacity());
	}

	@Override
	public void move() {
		System.out.println("Electric car is moving...");
	}

	public void go() {
		super.move();
		this.move();
	}

}