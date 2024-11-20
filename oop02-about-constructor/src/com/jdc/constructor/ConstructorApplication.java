package com.jdc.constructor;

import java.util.Scanner;

import com.jdc.constructor.model.Person;

public class ConstructorApplication {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Person person = new Person("U Tin Mg");

		System.out.print("Enter name: ");
		String name = sc.nextLine();
		person.setName(name);

		System.out.print("Enter age: ");
		int age = Integer.parseInt(sc.nextLine());
		person.setAge(age);

		System.out.print("Enter phone: ");
		String phone = sc.nextLine();
		person.setPhone(phone);

		System.out.println("""
							Name : %s
							Age  : %d
							Phone: %s""".formatted(person.getName(), 
												   person.getAge(),
												   person.getPhone()
												   )
						  );

		sc.close();
	}

}