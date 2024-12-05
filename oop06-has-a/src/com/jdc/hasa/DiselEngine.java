package com.jdc.hasa;

public class DiselEngine implements Engine {
	
	private String engineType;

	public DiselEngine(String engineType) {
		super();
		this.engineType = engineType;
	}

	public String getEngineType() {
		return engineType;
	}

	@Override
	public void start() {
		System.out.println("Disel engine is started.");
	}

	@Override
	public void stop() {
		System.out.println("Disel engine is stopped.");		
	}

}
