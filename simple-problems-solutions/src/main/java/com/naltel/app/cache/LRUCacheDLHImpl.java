package com.naltel.app.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheDLHImpl<K,V> extends LinkedHashMap<K, V> implements ICache<K,V> {

	private int size=10;
	public LRUCacheDLHImpl(int size) {
		this.size = size; 
	}
	
	@Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest){
        return size() > this.size;
    }
	 public static void main(String arg[]){
		 int size = 5;
		 LRUCacheDLHImpl<Integer, String> lruCache = new LRUCacheDLHImpl<Integer, String>(size);
	         
		 lruCache.put(1, "page1");
	        System.out.println(lruCache);
	        lruCache.put(2, "page2");
	        System.out.println(lruCache);
	        lruCache.put(3, "page3");
	        System.out.println(lruCache);
	        lruCache.get(1);
	        System.out.println(lruCache);
	        lruCache.put(4, "page4");
	        System.out.println(lruCache);
	        lruCache.put(5, "page5");
	        System.out.println(lruCache);
	        lruCache.get(3);
	        System.out.println(lruCache);
	        lruCache.put(6, "page6");
	        System.out.println(lruCache);
	        lruCache.get(5);
	        System.out.println(lruCache);
	        lruCache.put(7, "page7");
	        System.out.println(lruCache);
	        lruCache.get(5);
	        System.out.println(lruCache);
	        lruCache.put(8, "page8");
	        System.out.println(lruCache);
	        System.out.println(lruCache.get(2));
		    }
}
