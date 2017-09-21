package com.naltel.app.arrays;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSorted {
    public static class ArrayIndex {
        public int element;
        public int arrayindex;
        public int valueindex;

        ArrayIndex(int i, int j, int k) {
            element = i;
            arrayindex = j;
            valueindex = k;
        }
    }

    public static void main(String args[]) {
        int arr[][] = { {12, 30, 38},
                        {13, 23, 29},
                        {45, 72, 92}
                        };
        int arrsize = 3;
        PriorityQueue<ArrayIndex> queue = new PriorityQueue<ArrayIndex>(3, new Comparator<ArrayIndex>() {
            public int compare(ArrayIndex o1, ArrayIndex o2) {
                 if(o1.element > o2.element) return 1;
                 if(o1.element < o2.element) return -1;
                 return 0;
            }
        });
        for(int i = 0; i < 3; i++) {
            queue.add(new ArrayIndex(arr[i][0], i, 1));
        }
        while(!queue.isEmpty()) {
            ArrayIndex arrayIndex = queue.poll();
            System.out.println(arrayIndex.element + " ");
            if(arrayIndex.valueindex != arrsize) {
                queue.add(new ArrayIndex(arr[arrayIndex.arrayindex][arrayIndex.valueindex],arrayIndex.arrayindex, arrayIndex.valueindex+1));
            }
        }
    }
}
