package com.jdc.inheritance.model;

// Concrete Class
public final class ElectricCar extends Car {

	private int batteryCapacity;

	public ElectricCar(int door) {
		super(door); // hard code
		System.out.println("Electric Car constructor called.");
	}

	// property
	public void setBatteryCapacity(int batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}

	public int getBatteryCapacity() {
		return batteryCapacity;
	}

	@Override
	public void openDoor() {
		System.out.println("Electric Car door is opened.");
	}

	public void closeDoor() {
		System.out.println("Electric Car door is closed.");
	}

	public void chargeBattery() {
		System.out.println("Battery is charging...");
	}
}