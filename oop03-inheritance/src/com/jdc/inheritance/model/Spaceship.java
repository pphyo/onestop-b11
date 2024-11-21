package com.jdc.inheritance.model;

public class Spaceship extends Vehicle {
	private int shieldStrength;
	private int weaponPower;

	public int getShieldStrength() {
		return shieldStrength;
	}

	public void setShieldStrength(int shieldStrength) {
		this.shieldStrength = shieldStrength;
	}

	public int getWeaponPower() {
		return weaponPower;
	}

	public void setWeaponPower(int weaponPower) {
		this.weaponPower = weaponPower;
	}

	@Override
	public void startEngine() {
		System.out.println("Spaceship engine started.");
	}

	@Override
	public void stopEngine() {
		System.out.println("Spaceship engine stopped.");
	}

	public void activateShield() {
		System.out.println("Spaceship shield is activated.");
	}

	public void fireWeapon() {
		System.out.println("Spaceship weapon is fired.");
	}
}