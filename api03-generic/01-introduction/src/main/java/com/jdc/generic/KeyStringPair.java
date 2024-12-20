package com.jdc.generic;

public class KeyStringPair<V> implements Pair<String, V> {
	
	private String key;
	private V value;
	
	public KeyStringPair(String key, V value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}

}
