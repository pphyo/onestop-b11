package com.jdc.interf.app;

public interface ParentTwoInterf extends GrandParentInterf {
	
	void doTwo();
	
	@Override
	default void doJob() {
		System.out.println("Two's doJob()");		
	}
	
	static void initTwo() {
		System.out.println("static initOne()");
	}

}
