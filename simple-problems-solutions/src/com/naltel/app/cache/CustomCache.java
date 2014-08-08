package com.naltel.app.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CustomCache {

	private Map<Integer, CacheMetric> metrics;
	private ICache cache;
	
	@Override
	public String toString() {
		return cache.toString();
	}
	CustomCache(int size, String policy){
		metrics = new HashMap<Integer, CacheMetric>();
		if(policy.equalsIgnoreCase("lrucache"));
		cache = new LRUCacheDLHImpl<Integer, String>(size);	
	}
	
	public void getMetrics() {
		
		for(Entry<Integer, CacheMetric> cacheMetric: metrics.entrySet()) {
			Integer key = cacheMetric.getKey();
			CacheMetric metric = cacheMetric.getValue();
			System.out.println("key "+key +" metric "+ (double)metric.getMemRef()/(double)metric.getKeyRef());
		}
	}
	
	public Double getMetric(Integer key) {
		
		CacheMetric metric = metrics.get(key);
		if(metric != null) {
			System.out.println("Key "+key+" memRef "+metric.getMemRef()+ " keyRef "+metric.getKeyRef());
			
			return (double) ((double)metric.getMemRef()/(double)metric.getKeyRef());
		}
		else return null;
	}
	@SuppressWarnings("unchecked")
	public String put(Integer key, String pageNumber){
		return (String) cache.put(key, pageNumber);
	}
	
	@SuppressWarnings("unchecked")
	public String get(Integer key) {
		String val =  (String) cache.get(key);
		CacheMetric metric = metrics.get(key);
		if(metric == null) {
			metric = new CacheMetric();
		}
		metric.setKeyRef(metric.getKeyRef()+1);
		if(val != null) {
			metric.setMemRef(metric.getMemRef()+1);
		}
		metrics.put(key, metric);
		return val;
	}
}
