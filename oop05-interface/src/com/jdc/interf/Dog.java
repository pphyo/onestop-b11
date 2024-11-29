package com.jdc.interf;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Dog extends Animal implements CanRun, Serializable, Runnable {

	@Override
	public void run() {
		System.out.println("Dog runs.");		
	}
	
}
