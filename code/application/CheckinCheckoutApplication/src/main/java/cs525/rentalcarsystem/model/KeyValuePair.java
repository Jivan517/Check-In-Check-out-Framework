/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */
package cs525.rentalcarsystem.model;

/**
 * @author OWNER
 *
 */
public class KeyValuePair<K, V> {

	private K key;
	private V value;

	public KeyValuePair() {

	}

	public KeyValuePair(K key, V value) {
		this.setKey(key);
		this.setValue(value);
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value.toString();
	}

}
