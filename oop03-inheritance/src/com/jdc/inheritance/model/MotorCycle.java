package com.jdc.inheritance.model;

public class MotorCycle extends Vehicle {
	private int engineCC;
	public int year = 2012;

	public void setEngineCC(int engineCC) {
		this.engineCC = engineCC;
	}

	public int getEngineCC() {
		return engineCC;
	}

	public void revEngine() {
		System.out.println("Motor Cycle engine is revved.");
	}

	@Override // Covarient
	public Car getSelf() {
		return null;
	}

}