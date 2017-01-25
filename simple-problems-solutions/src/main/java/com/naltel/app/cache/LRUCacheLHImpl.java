package com.naltel.app.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheLHImpl<K,V> extends LinkedHashMap<K, V> {
		    private int size;
	     
	    public LRUCacheLHImpl(int size, float loadFactor){
	        super(size, loadFactor, true);
	        this.size = size;
	    }
	    
	    public LRUCacheLHImpl(int capacity){
	        super(capacity);
	        this.size = capacity;
	    }
	    @Override
	    protected boolean removeEldestEntry(Map.Entry<K, V> eldest){
	        return size() > this.size;
	    }
	     
	    public static void main(String arg[]){
	    	LRUCacheLHImpl<Integer, String> lruCache = new LRUCacheLHImpl<Integer, String>(5);
	         
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
