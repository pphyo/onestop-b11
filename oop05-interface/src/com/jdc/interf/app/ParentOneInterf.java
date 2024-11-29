package com.jdc.interf.app;

public interface ParentOneInterf extends GrandParentInterf {

	void doOne();
	
	@Override
	default void doJob() {
		System.out.println("One's doJob()");		
	}
	
	static void initOne() {
		System.out.println("static initOne()");
	}
	
}
