package com.jdc.isp;

public class BearKeeper implements CanFeed, CanClean, CanPet {
	
	@Override
	public void clean() {
		System.out.println("Bear keeper cleans the bear.");		
	}
	
	@Override
	public void feed() {
		System.out.println("Bear keeper feeds the bear.");		
	}
	
	@Override
	public void pet() {
		System.out.println("Bear keeper pets the bear.");		
	}

}
