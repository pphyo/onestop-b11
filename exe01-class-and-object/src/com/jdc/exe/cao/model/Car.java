package com.jdc.exe.cao.model;

public class Car {

	public String model;
	public String color;
	public int door;
	public int wheel;
	public int travelledDistance;

	public static int maxSpeed = 0;

	public void move(int km, int speed) {
		System.out.println("Car is moving with " + km + " km.");
		changeMovedDistance(km);

		if(speed > maxSpeed) {
			maxSpeed = speed;
		}
	}

	public void changeMovedDistance(int distance) {
		this.travelledDistance += distance;
	}

	public void showCarInfo() {
		System.out.println("Model: " + model);
		System.out.println("Color: " + color);
		System.out.println("Wheel: " + wheel);
		System.out.println("Door: " + door);
	}
}