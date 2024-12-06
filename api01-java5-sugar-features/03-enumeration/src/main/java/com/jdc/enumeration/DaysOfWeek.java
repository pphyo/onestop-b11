package com.jdc.enumeration;

public enum DaysOfWeek {
	
	SUN, MON, TUE, WED, THU, FRI, SAT;
	
	{
		System.out.println("Instance block in Enum");
	}
	
	static {
		System.out.println("Static block in Enum");
	}
	
	private DaysOfWeek() {
		System.out.println("Constructor in Enum");
	}

}
