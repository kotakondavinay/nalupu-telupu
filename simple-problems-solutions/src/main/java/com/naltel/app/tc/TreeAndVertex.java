package com.naltel.app.tc;

import java.util.Arrays;
import java.util.Collections;

public class TreeAndVertex {

    public int get(int[] tree) {
        Integer[] deg = new Integer[tree.length+1];
        for(int i = 0; i < deg.length; i++) {
            deg[i] = new Integer(0);
        }
        for( int i = 0; i < tree.length; i++) {
            deg[i+1]++;
            deg[tree[i]]++;
        }
        return Collections.max(Arrays.asList(deg));
    }

    public static void main(String[] args) {
        TreeAndVertex tv = new TreeAndVertex();
        int[] edge1 = {0,0,0};
        System.out.println(tv.get(edge1));
        int[] edge2 = {0, 1, 2, 3};
        System.out.println(tv.get(edge2));
        int[] edge3 = {0, 0, 2, 2};
        System.out.println(tv.get(edge3));
        int[] edge4 = {0, 0, 0, 1, 1, 1};
        System.out.println(tv.get(edge4));
        int[] edge5 = {0, 1, 2, 0, 1, 5, 6, 1, 7, 4, 2, 5, 5, 8, 6, 2, 14, 12, 18, 10, 0, 6, 18, 2, 20, 11, 0, 11, 7, 12, 17, 3, 18, 31, 14, 34, 30, 11, 9};
        System.out.println(tv.get(edge5));
    }
}
