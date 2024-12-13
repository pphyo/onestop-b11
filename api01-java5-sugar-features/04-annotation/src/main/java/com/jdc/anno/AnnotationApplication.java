package com.jdc.anno;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationApplication {
	
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		var obj = new UsingAnnotation();
//		obj.setVar1(7);
		
		Field[] fields = UsingAnnotation.class.getDeclaredFields();
		Method[] methods = UsingAnnotation.class.getDeclaredMethods();
		
		for(var method : methods) {
			if(method.isAnnotationPresent(Value.class)) {
				var anno = method.getAnnotation(Value.class);
				method.invoke(obj, anno.value(), anno.name());
			}
		}
		
		for(var field : fields) {
			if(field.isAnnotationPresent(Value.class)) {
				field.setAccessible(true);
				
				var anno = field.getAnnotation(Value.class);
				
				if(field.getType().equals(int.class)) {
					
					if(((Integer) field.get(obj)) > 0 ) {
						System.out.println(field.get(obj));
					} else {
						field.set(obj, anno.value()[2]);
						System.out.println(field.get(obj));
					}
				}
			}
		}
		
	}

}
