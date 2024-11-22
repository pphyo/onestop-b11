package com.jdc.inheritance.model;

public class Car extends Vehicle {

	private int door;

	public Car(int door) {
		this.door = door;
		System.out.println("Car constructor called.");
	}

	public int getDoor() {
		return door;
	}

	public void setDoor(int door) {
		this.door = door;
	}

	public void openDoor() {
		System.out.println("Car door is opened.");
	}

	public void closeDoor() {
		System.out.println("Car door is closed.");
	}
}