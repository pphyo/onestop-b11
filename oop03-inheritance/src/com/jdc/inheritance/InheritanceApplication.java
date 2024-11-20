package com.jdc.inheritance;

public class InheritanceApplication {
	
	public static void main(String[] args) {
		new InheritanceApplication().launch();
	}

	private void launch() {
		Car car = new Car();

		car.setMake("Tesla");
		car.setModel("v8");
		car.setYear(2018);

		System.out.println("""
							Make : %s
							Model: %s
							Year : %s"""
							.formatted(car.getMake(), car.getModel(), car.getYear())
						  );			
		car.startEngine();
		car.stopEngine();
	}

}