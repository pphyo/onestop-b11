package com.jdc.inheritance;

public class Car extends Vehicle {
	private int door;

	public Car() {
		System.out.println("Car constructor called.");
	}

	public int getDoor() {
		return door;
	}

	public void setDoor(int door) {
		this.door = door;
	}
}