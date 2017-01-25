package com.naltel.app.cache;

public interface ICache<K,V> {
	V put(K key, V val);
	V get(K key);
}