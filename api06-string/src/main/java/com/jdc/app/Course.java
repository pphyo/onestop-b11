package com.jdc.app;

import java.util.Objects;

public class Course {

	private String name;
	private double fees;
	private int duration;

	public Course(String name, double fees, int duration) {
		this.name = name;
		this.fees = fees;
		this.duration = duration;
	}

	@Override
	public int hashCode() {
		return Objects.hash(duration, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return duration == other.duration && Objects.equals(name, other.name);
	}

	public String getName() {
		return name;
	}

	public double getFees() {
		return fees;
	}

	public int getDuration() {
		return duration;
	}

}
