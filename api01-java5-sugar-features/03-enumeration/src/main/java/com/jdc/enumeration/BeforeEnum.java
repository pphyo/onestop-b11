package com.jdc.enumeration;

public class BeforeEnum {
	
	public static final int SUN = 0;
	public static final int MON = 1;
	public static final int TUE = 2;
	public static final int WED = 3;
	public static final int THU = 4;
	public static final int FRI = 5;
	public static final int SAT = 6;
	
	{
		System.out.println("Instance block");
	}
	
	public BeforeEnum() {
		super();
		System.out.println("Const");
	}
	
	public static void main(String[] args) {
		System.out.println(new BeforeEnum());
	}
	
	public static String showDay(int code) {
		int day = MON;
		day = 10;
		return switch(day) {
			case 1, 2, 3, 4, 5 -> "Weekdays";
			case 0, 6 -> "Weekend";
			default -> "Wrong code";
		};
	}

}









