package com.jdc.converter.json;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

import com.jdc.converter.JsonException;
import com.jdc.converter.domain.Json;
import com.jdc.converter.domain.JsonIgnore;
import com.jdc.converter.domain.JsonKey;

public class JsonConverter {

	public static String toJson(Object object) throws IllegalArgumentException, IllegalAccessException {
		check(object);
		return convert(object);
	}
	
	private static String convert(Object object) throws IllegalArgumentException, IllegalAccessException {
		var jsonBuilder = new StringBuilder();
		jsonBuilder.append("{\n\t");
		
		Class<?> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		int counter = 0;
		for(Field field : fields) {
			field.setAccessible(true);
			
			if(field.isAnnotationPresent(JsonIgnore.class))
				continue;
			
			counter ++;
			// default key name
			String jsonKey = field.getName();
			
			// override key name when present @JsonKey
			if(field.isAnnotationPresent(JsonKey.class))
				jsonKey = field.getAnnotation(JsonKey.class).value();

			Object value = field.get(object);
			
			jsonBuilder.append("\"")
					   .append(jsonKey)
					   .append("\": ")
					   .append(serializeValue(value));
			
			if(counter != fields.length - 1) {
				jsonBuilder.append(",\n\t");
			}
						
		}
		
		jsonBuilder.append("\n}");
		
		return jsonBuilder.toString();
		
	}
	
	private static String serializeValue(Object value) {
		String result = null;
		
		if(Objects.isNull(value)) {
			result = "null";
		}
		
		// check string, character
		if(value instanceof String || value instanceof Character) {
			result = "\"" + value + "\"";
		}
		
		// check boolean, number
		if(value instanceof Boolean || value instanceof Number) {
			result = value.toString();
		}
		
		// check array of string
		if(value instanceof String[] arr) {
			result = convertJsonStringArray(arr);
		}
		
		// check array of character
		if(value instanceof Character[] || value instanceof char[]) {
			Character[] convertedArray = null;
			
			if(value instanceof char[] arr) {
				convertedArray = new Character[arr.length];
				for(int i = 0; i < convertedArray.length; i++) {
					convertedArray[i] = arr[i];
				}
			} else {
				convertedArray = (Character[]) value;
			}
			
			result = convertJsonStringArray(convertedArray);
		}
		
		// check array of number or boolean
		if(value instanceof byte[] || value instanceof short[] ||
				value instanceof int[] || value instanceof long[] ||
				value instanceof double[] || value instanceof float[] || value instanceof boolean[])
			result = switch(value) {
						case byte[] byteArray -> Arrays.toString((byte[]) value);
						case short[] shortArray -> Arrays.toString((short[]) value);
						case int[] intArray -> Arrays.toString((int[]) value);
						case long[] longArray -> Arrays.toString((long[]) value);
						case float[] floatArray -> Arrays.toString((float[]) value);
						case double[] doubleArray -> Arrays.toString((double[]) value);
						case boolean[] boolArray -> Arrays.toString((boolean[]) value);
						default -> throw new JsonException("Error!");
					};
		
		return result;
	}
	
	private static String convertJsonStringArray(Object[] array) {
		var result = Arrays.toString(array);
		
		result = result.replace("[", "[\"");
		result = result.replaceAll(", ", "\", \"");
		result = result.replace("]", "\"]");
		
		return result;
	}
	
	private static void check(Object object) {
		if(Objects.isNull(object)) {
			throw new JsonException("Null object can't convert to Json!");
		}
		
		if(!object.getClass().isAnnotationPresent(Json.class)) {
			throw new JsonException("%s is not annotated with @Json. So cannot convert to Json."
											.formatted(object.getClass().getName()));
		}
	}

}


