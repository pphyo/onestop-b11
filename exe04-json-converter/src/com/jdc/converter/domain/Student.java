package com.jdc.converter.domain;

@Json
public class Student {

	private String name;
	
	@JsonIgnore
	private String phone;
	private int age;
	private int[] marks;
	
	@JsonKey("average_mark")
	private double avg;
	private boolean primary;
	private String[] subjects;
	private Character[] grades;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int[] getMarks() {
		return marks;
	}

	public void setMarks(int[] marks) {
		this.marks = marks;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

	public String[] getSubjects() {
		return subjects;
	}

	public void setSubjects(String[] subjects) {
		this.subjects = subjects;
	}

	public Character[] getGrades() {
		return grades;
	}

	public void setGrades(Character[] grades) {
		this.grades = grades;
	}

}
