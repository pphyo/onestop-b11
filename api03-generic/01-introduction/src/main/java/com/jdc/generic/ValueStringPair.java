package com.jdc.generic;

public class ValueStringPair<K> implements Pair<K, String> {

	private K key;
	private String value;

	public ValueStringPair(K key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}

}
