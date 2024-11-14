// create car object
// set model, color, door, wheel to created object
// move with distance 
// print out travelledDistance
package com.jdc.exe.cao;

import com.jdc.exe.cao.model.Car;

public class MainApplication {

	public static void main(String[] args) {
		var audi = new Car();
		audi.model = "Audi 2011";
		audi.color = "Sky Blue";
		audi.wheel = 4;
		audi.door = 4;
		audi.showCarInfo();

		audi.move(10, 30);
		showDistanceAndSpeed(audi);
		audi.move(30, 24);
		showDistanceAndSpeed(audi);
		audi.move(50, 42);
		showDistanceAndSpeed(audi);
	}

	static void showDistanceAndSpeed(Car car) {
		System.out.println("Distance: " + car.travelledDistance);
		System.out.println("Max Speed: " + car.maxSpeed);
	}

}

// 10, 30
// print td, ms
// 30, 24
// print td, ms
// 50, 42
// print td, ms