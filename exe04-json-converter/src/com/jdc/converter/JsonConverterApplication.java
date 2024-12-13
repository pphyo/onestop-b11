package com.jdc.converter;

import java.util.Arrays;

import com.jdc.converter.domain.Student;
import com.jdc.converter.json.JsonConverter;

public class JsonConverterApplication {
	
	public static void main(String[] args) {
		
		Student student = new Student();
		student.setName("Kaung Kaung Kyaw");
		student.setAge(18);
		student.setPhone("09494939944");
		student.setMarks(new int[] {40, 50, 60});
		student.setAvg(Arrays.stream(student.getMarks()).average().getAsDouble());
		student.setPrimary(false);
		student.setSubjects(new String[] {"Myanmar", "English", "Maths"});
		student.setGrades(new Character[] {'D', 'D', 'C'});
		
		try {
			System.out.println(JsonConverter.toJson(student));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
