package com.jdc.au;

import static java.lang.System.out;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class AutoboxUnboxApplication {

	@SuppressWarnings({"removal", "unused"})
	public static void main(String[] args) {
		int priInt = 10;
		Integer hpInt = new Integer(20);
		long priLong = 30L;
		Long hpLong = new Long(40L);
		
		// primitive and wrapper type are same
		Integer fromPriInt = priInt; // Integer.valueOf(priInt) auto boxing
		int fromHpInt = hpInt; // hpInt.intValue() auto unboxing
		Long fromPriLong = priLong; // Long.valueOf(priLong) auto boxing
		long fromHpLong = hpLong; // hpLong.longValue() auto unboxing
		
		// if primitive and wrapper type are not same
		// Long fromPriIntToHpLong = priInt; // error bcuz priInt auto box to Integer so Integer and Long are not the same type
		long fromHpIntToPriLong = hpInt;
		
		out.println(Byte.MIN_VALUE);
		out.println(Byte.MAX_VALUE);
		out.println(MIN_VALUE);
		out.println(MAX_VALUE);
		out.println(min(10, 20));
		out.println(max(10, 20));
		
		float priFloat = 10.32f;
		Float hpFloat = new Float(20.45f);
		double priDouble = 30.44d;
		Double hpDouble = new Double(40.55d);

	}

}
