package com.jdc.inheritance;

import com.jdc.inheritance.model.*;

public class InheritanceApplication {
	
	public static void main(String[] args) {
		new InheritanceApplication()
			.launchFive(new Car(1));
	}

	private void launchFive(Vehicle veh) {
		System.out.println("Start");

		// pattern matching
		if(veh instanceof Car car) {
			// Car car = (Car) veh;
			car.openDoor();
			car.closeDoor();
		}

		veh.startEngine();
		veh.stopEngine();

		System.out.println("Terminate");
		// ((Spaceship) veh).activateShield();
		// ((Spaceship) veh).fireWeapon();
	}

	private void launchFour() {
		Book book = new Book("", 0, "", 0, "");
		System.out.println(book);
	}

	private void launchThree() {
		Calculator.calculate((byte)10);
	}

	private void launchTwo() {
		Vehicle veh = new MotorCycle();
		System.out.println(veh.year);
		veh.startEngine();
		// veh.activateShield(); // because veh is type of Vehicle
		veh.stopEngine();

		// Vehicle truck = new Vehicle();
		// truck.startEngine();
		// truck.stopEngine();

		// Vehicle mc = new MotorCycle();
		// mc.startEngine();
		// mc.stopEngine();

		// Vehicle enterprise = new Spaceship();
		// enterprise.startEngine();
		// enterprise.stopEngine();
	}

	private void launchOne() {
		var car = new ElectricCar(6);
		car.setMake("Tesla");
		car.setModel("v8");
		car.setYear(2018);
		car.setBatteryCapacity(21000);

		System.out.println("""
							Make : %s
							Model: %s
							Year : %s
							Door : %s"""
							.formatted(car.getMake(), car.getModel(),
								car.getYear(), car.getDoor())
						  );			
		car.startEngine(); // inherit from Vechile
		car.stopEngine(); // inherit from Vechile
		car.openDoor(); // inherit from Car
		car.closeDoor(); // inherit from Car
		car.chargeBattery(); // it's own behaviour

		ElectricCar.countUp();
		System.out.println("Count: " + ElectricCar.getCount());
	}

}