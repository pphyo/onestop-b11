package com.jdc.hasa;

public class Car {

	private String make;
	private String model;
	private int door;
	private Engine engine;

	public Car(String make, String model, int door, Engine engine) { // weaker type of association => aggregation
		super();
		this.make = make;
		this.model = model;
		this.door = door;
		this.engine = engine;
	}
	
	public Engine getEngine() {
		return engine;
	}
	
	public void move() {
		engine.start();
		System.out.println("Car is moving...");
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getDoor() {
		return door;
	}

}
