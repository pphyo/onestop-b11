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
		jsonBuilder.append("{");
		
		Class<?> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		for(Field field : fields) {
			field.setAccessible(true);
			
			if(field.isAnnotationPresent(JsonIgnore.class))
				continue;
			
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
			
		}
		
		jsonBuilder.append("}");
		
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
		
		// check array of string or character
		if(value instanceof String[] || value instanceof Character[]) {
			System.out.println(Arrays.toString(value instanceof String[] ? (String[]) value : (Character[]) value));
		}
		
		// check array of number or boolean
		if(value instanceof int[] array) {
			System.out.println(Arrays.toString(array));
		}
		
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


