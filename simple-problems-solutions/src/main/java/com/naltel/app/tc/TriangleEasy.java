package com.naltel.app.tc;

import java.util.*;

public class TriangleEasy {
    public int find(int n, int[] x, int[] y) {
        if(x.length == 0) return 3;
        Map<Integer, Set<Integer>> edgeMap = new HashMap<>();
        for( int i = 0; i < x.length; i++) {
            int x1 = x[i];
            int y1 = y[i];
            int key = x1 < y1 ? x1: y1;
            int val = x1 > y1 ? x1: y1;
            if( edgeMap.containsKey(key)) {
                edgeMap.get(key).add(val);
            } else {
                Set<Integer> set = new HashSet<>();
                set.add(val);
                edgeMap.put(key, set);
            }
        }
        boolean isTwoExists = false;
        for(Integer mapKey: edgeMap.keySet()) {
            Set<Integer> values =edgeMap.get(mapKey);
            if(values.size() > 1) {
                for(Integer setVal: values) {
                    if(edgeMap.containsKey(setVal)) {
                        if(!Collections.disjoint(values, edgeMap.get(setVal))) {
                            return 0;
                        }
                    }
                }
                isTwoExists = true;
            }
        }
        if(isTwoExists) return 1;

        return 2;
    }


    public static void main(String[] args) {

        TriangleEasy te = new TriangleEasy();
        int n1 = 3;
        int[] x1 = {};
        int[] y1 = {};
        System.out.println(te.find(n1, x1, y1));

        int n2 = 4;
        int[] x2= {0,2,1,2};
        int[] y2 = {3,0,2,3};
        System.out.println(te.find(n2, x2, y2));

        int n3 = 6;
        int[] x3 = {0,0,2};
        int[] y3 = {3,1,4};
        System.out.println(te.find(n3, x3, y3));

        int n4 = 4;
        int[] x4 = {0,2};
        int[] y4 = {1,3};
        System.out.println(te.find(n4, x4, y4));

        int n5 = 20;
        int[] x5 ={16,4,15,6,1,0,10,12,7,15,2,4,8,1,10,15,13,10,1,16,3,19,8,7,13,1,15,15,15,5,16,7,5,6,4,18,3,8,6,2,16,8,19,14,17,16,4,6,9,17,4,10,8,12,2,3,18,9,13,17,4,7,10,0,13,11,15,17,11,15,11,19,19,4,10,14,16,6,3,17,1,4,14,9,7,18,10,11,5,0,5,9,9,7,16,12,4,10,17,3};
        int[] y5 = {17,18,6,16,18,6,11,2,15,10,1,15,17,8,5,9,7,0,0,4,16,1,9,0,9,5,17,14,1,12,14,11,9,18,0,12,11,3,19,14,7,6,3,19,0,1,19,5,11,19,2,13,12,0,6,2,14,16,14,18,5,5,19,3,6,14,12,5,17,3,1,12,7,11,8,8,10,11,13,2,13,13,0,18,2,7,2,12,14,9,3,19,2,8,12,13,8,18,13,18};
        System.out.println(te.find(n5, x5, y5));

        int n6 = 4;
        int[] x6 = {0,1,2};
        int[] y6 = {1,0,3};
        System.out.println(te.find(n6, x6, y6));

    }
}
