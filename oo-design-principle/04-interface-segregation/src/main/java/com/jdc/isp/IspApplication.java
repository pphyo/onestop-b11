package com.jdc.isp;

public class IspApplication {
	
	public static void main(String[] args) {
		
		var cleaner = new BearCleaner();
		cleaner.clean();
		
		var feeder = new BearFeeder();
		feeder.feed();
		
		var petter = new BearPetter();
		petter.pet();
		
		var keeper = new BearKeeper();
		keeper.clean();
		keeper.feed();
		keeper.pet();
		
	}

}
