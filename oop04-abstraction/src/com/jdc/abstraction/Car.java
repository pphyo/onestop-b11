package com.jdc.abstraction;

public abstract class Car extends Vehicle {

	private int door;

	public Car(String model, String color, int year, int door) {
		super(model, color, year);
		this.door = door;
	}

	public int getDoor() {
		return door;
	}

	@Override
	public abstract void startEngine();

	@Override
	public abstract void stopEngine();


}