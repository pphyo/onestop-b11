package com.jdc.hasa;

public class CNGEngine implements Engine {

	private String gasType;

	public CNGEngine(String gasType) {
		super();
		this.gasType = gasType;
	}

	public String getGasType() {
		return gasType;
	}

	@Override
	public void start() {
		System.out.println("CNG Engine is started.");
	}

	@Override
	public void stop() {
		System.out.println("CNG Engine is stopped.");
	}

}
