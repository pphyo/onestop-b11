package com.jdc.constructor;

import java.util.Scanner;

public class ConstructorApplication {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter age: ");
		int age = sc.nextInt();

		Person person = new Person("U Tin Mg");
		person.setAge(age);
		System.out.println("Age: ");

		sc.close();
	}

}