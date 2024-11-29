package com.jdc.interf;

import java.io.Serializable;

import com.jdc.interf.app.ChildClazz;
import com.jdc.interf.app.ParentOneInterf;

public class InterfaceApplication {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		CanRun cr = new Dog();
		cr.run();
		cr.start();
		CanRun.init();
		
		// anonymous inner class
		// extends some class or implements some interface
		CanRun anon = new CanRun() {
			
			@Override
			public void run() {
				System.out.println("Anon runs.");				
			}
			
		};
		anon.run();
		
		// Dog class is a polymorphic class
		var dog = new Dog();
		Animal ani = dog;
		CanRun run = dog;
		Runnable runnable = dog;
		Serializable ser = dog;
		
		ParentOneInterf one = new ChildClazz();
		one.doJob();
		one.doOne();
		System.out.println(ParentOneInterf.DATA);
		ParentOneInterf.initOne();
		
	}

}








