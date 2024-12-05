package com.jdc.hasa;

public class MainApplication {

	public static void main(String[] args) {
		
		Person p = new Person(new Car("Audi", "2022", 4, new CNGEngine("CNG")));
		p.drive();

	}

}
