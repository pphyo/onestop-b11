package com.jdc.generic;

public class KeyValueStringPair implements Pair<String, String> {

	private String key;
	private String value;

	public KeyValueStringPair(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

}
