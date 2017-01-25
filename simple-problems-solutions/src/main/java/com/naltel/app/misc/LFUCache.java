/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naltel.app.misc;

import java.util.*;

/**
 *
 * @author vinaykk
 */
public class LFUCache {

    Map<Integer, CacheNode> cache;
    Integer _maxCacheSize;
    Integer _lowestFrequency;
    Integer _maxFrequency;
    LinkedHashSet<CacheNode>[] frequencyList;

    public LFUCache(int capacity) {
        //if(capacity > 0)
        cache = new HashMap<Integer, CacheNode>(capacity);
        //else 
        //    cache = new HashMap<Integer, CacheNode>();
        _maxCacheSize = capacity;
        frequencyList = new LinkedHashSet[capacity];
        _lowestFrequency = 0;
        _maxFrequency = capacity - 1;
        initFrequencyList();
    }

    private void initFrequencyList() {
        for (int i = 0; i <= _maxFrequency; i++) {
            frequencyList[i] = new LinkedHashSet<CacheNode>();
        }
    }

    private void doEviction() {
        LinkedHashSet<CacheNode> nodes = frequencyList[_lowestFrequency];
        CacheNode nodeToRemove = nodes.iterator().next();
        nodes.remove(nodeToRemove);
        cache.remove(nodeToRemove.k);
        if(nodes.isEmpty()) {
            for(int i = _lowestFrequency+1 ; i <= _maxFrequency ; i++) {
                if(!frequencyList[i].isEmpty()) {
                    _lowestFrequency = i;
                }
            }
        }
    }

    public int get(int key) {
        CacheNode returnNode = cache.get(key);
        if (returnNode != null) {

            int currentFrequency = returnNode.frequency;
            if (currentFrequency < _maxFrequency) {
                int nextFrequency = currentFrequency + 1;
                LinkedHashSet<CacheNode> currentFrequencyList = frequencyList[currentFrequency];
                LinkedHashSet<CacheNode> nextFrequencyList = frequencyList[nextFrequency];
                currentFrequencyList.remove(returnNode);
                nextFrequencyList.add(returnNode);
                returnNode.frequency = nextFrequency;
                cache.put(key, returnNode);
                if (_lowestFrequency == currentFrequency && currentFrequencyList.isEmpty()) {
                    _lowestFrequency = nextFrequency;
                }
            } else {
                LinkedHashSet<CacheNode> currentFrequencyList = frequencyList[currentFrequency];
                currentFrequencyList.remove(returnNode);
                currentFrequencyList.add(returnNode);
            }
            return returnNode.v;
        } else {
            return -1;
        }
    }

    public void set(int key, int value) {
        CacheNode currentNode = cache.get(key);
        if (currentNode == null) {
            if(_maxCacheSize <= 0) return;
            if (cache.size() == _maxCacheSize) {
                doEviction();
            }
            LinkedHashSet<CacheNode> nodes = frequencyList[0];
            currentNode = new CacheNode(key, value, 0);
            nodes.add(currentNode);
            cache.put(key, currentNode);
            _lowestFrequency = 0;
           // get(key);
        } else {
            currentNode.v = value;
            get(key);
        }
    }

    private static class CacheNode {

        public final Integer k;
        public Integer v;
        public int frequency;

        public CacheNode(Integer k, Integer v, int frequency) {
            this.k = k;
            this.v = v;
            this.frequency = frequency;
        }
    }
}
