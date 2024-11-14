package com.jdc.cao;

import com.jdc.cao.model.Hero;

public class MainApplication {

	public static void main(String[] args) {
		// fully qualified name => com.jdc.cao.model.Hero
		// object creation, initialization, init
		System.out.println("Hero count: " + Hero.heroCount);
		Hero.countUp();
		System.out.println("Hero count: " + Hero.heroCount);
		// Hero.fight(); //error

		Hero spiderman = new Hero();
		spiderman.name = "Spider-Man";
		spiderman.fight();
		spiderman.countUp();
		System.out.println("Hero count: " + Hero.heroCount);

		Hero ironman = new Hero();
		ironman.name = "Iron Man";
		ironman.fight();
		ironman.countUp();
		System.out.println("Hero count: " + Hero.heroCount);

		System.out.println("Spider's name: " + spiderman.name);
		System.out.println("Spider's name: " + spiderman.name);
	}

}