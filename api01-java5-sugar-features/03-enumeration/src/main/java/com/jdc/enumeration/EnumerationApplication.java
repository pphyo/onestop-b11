package com.jdc.enumeration;

public class EnumerationApplication {

	public static void main(String[] args) {

		showAllCourse();

	}
	
	static void showAllCourse() {
		Course[] courses = Course.values();
		
		for(Course course : courses) {
			System.out.printf("Course: %s, Fees: %d, Discount Price: %.2f%n", 
						course.courseName(), course.fees(), course.discount());
		}
	}
	
	static void showDaysOfWeek() {
		DaysOfWeek[] values = DaysOfWeek.values();
		
		for(DaysOfWeek dow : values) {
			System.out.println(dow.ordinal() + " : " + dow.name());
		}
	}

}
