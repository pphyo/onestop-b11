package com.jdc.collection.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MapInterfaceTest {
	
	static HashMap<String, String> hm;
	static TreeMap<String, String> tm;
	static LinkedHashMap<String, String> lhm;
	
	@Test
	void test_for_hash_map() {
		assertEquals(6, hm.size());
		assertNull(hm.put("Columbia", "Rodriguez"));
		assertEquals(7, hm.size());
		
		assertEquals("Iniasta", hm.put("Spain", "Pique"));
		assertEquals(7, hm.size());
		
		hm.put("Brazil", "Pele");
		
		assertEquals("Pele", hm.get("Brazil"));
		
		// get all keys
		Set<String> keys = hm.keySet();
		System.out.println(keys);
		
		// get all values
		List<String> values = new ArrayList<String>(hm.values());
		System.out.println(values);
		
		// get all entries
		Set<Entry<String, String>> entries = hm.entrySet();
		for(Entry<String, String> entry : entries) {
			if(entry.getKey() == "France") {
				entry.setValue("Benzema");
			}
			System.out.println("%s : %s".formatted(entry.getKey(), entry.getValue()));
		}
		
	}
	
	@BeforeAll
	static void init() {
		Map<String, String> map = Map.of(
				"England", "David Beckham",
				"Brazil", "Ronaldo",
				"Argentina", "Maradona",
				"France", "Zidane",
				"Spain", "Iniasta",
				"Portugul", "Ronaldo"
				);
		
		hm = new HashMap<>(map);
		tm = new TreeMap<>(map);
		lhm = new LinkedHashMap<>(map);
	}
	
}

