package com.jdc.enumeration;

public enum Course {

	JSE("Java SE", 300_000) {
		@Override
		public double discount() {
			return fees() - (fees() * 0.05);
		}
	}, // 300_000
	JEE("Java EE", 400_000) {
		@Override
		public double discount() {
			return fees() - (fees() * 0.07);
		}
	}, // 400_000
	SPRING("Spring Framework", 450_000) {
		@Override
		public double discount() {
			return fees() - (fees() * 0.1);
		}
	}, // 450_000
	NG("Angular", 350_000) {
		@Override
		public double discount() {
			return fees() - (fees() * 0.05);
		}
	}, // 350_000
	REACT("React JS", 400_000) {
		@Override
		public double discount() {
			return fees() - (fees() * 0.05);
		}
	}; // 400_000

	private String courseName;
	private int fees;

	Course(String courseName, int fees) {
		this.courseName = courseName;
		this.fees = fees;
	}

	public String courseName() {
		return courseName;
	}

	public int fees() {
		return fees;
	}

	public abstract double discount();

}