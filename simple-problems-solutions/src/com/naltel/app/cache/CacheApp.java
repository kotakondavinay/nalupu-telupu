package com.naltel.app.cache;

public class CacheApp {

	public static void main(String args[]) {
		CustomCache cache = new CustomCache(5, "lrucache");
		cache.put(1, "page1");
        System.out.println(cache);
        cache.put(2, "page2");
        System.out.println(cache);
        cache.get(2);
        System.out.println(cache);
        cache.put(3, "page3");
        System.out.println(cache);
        cache.get(1);
        System.out.println(cache);
        cache.put(4, "page4");
        System.out.println(cache);
        cache.put(5, "page5");
        System.out.println(cache);
        cache.get(3);
        System.out.println(cache);
        cache.put(6, "page6");
        System.out.println(cache);
        cache.get(5);
        System.out.println(cache);
        cache.put(7, "page7");
        System.out.println(cache);
        cache.get(5);
        System.out.println(cache);
        cache.put(8, "page8");
        System.out.println(cache);
        System.out.println(cache.get(2));
        System.out.println(cache.getMetric(2));
        cache.getMetrics();
	}
}
