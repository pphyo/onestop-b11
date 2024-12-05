package com.jdc.hasa;

public class Person {
	
	private Car car;
	
	public Person(Car car) {
//		car = new Car("Audi", "2022", 4, new CNGEngine("CNG")); // stronger type of association
		this.car = car;
	}
	
	public Car getCar() {
		return car;
	}
	
	public void drive() {
		System.out.println("Person is driving the car.");
		car.move();
	}

}
