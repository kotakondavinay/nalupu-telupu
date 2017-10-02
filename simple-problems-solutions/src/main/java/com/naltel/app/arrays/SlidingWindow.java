package com.naltel.app.arrays;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindow {

    static void printMax(int[] arr, int k) {
        Deque<Integer> q = new ArrayDeque<>();
        int i = 0;
        for(; i<k; i++) {
            while(!q.isEmpty() && arr[i] >= arr[q.peekLast()]) {
                q.removeLast();
            }
            q.addLast(i);
        }

        for(; i < arr.length; i++) {
            System.out.print( arr[q.peek()] + " ");
            if(q.peek() <= i-k) {
                q.removeFirst();
            }
            while(!q.isEmpty() && arr[i] >= arr[q.peekLast()]) {
                q.removeLast();
            }
            q.addLast(i);
        }
        System.out.println(arr[q.peek()]);
    }

    public static void main(String[] args)
    {
        int arr[]={12, 1, 78, 90, 57, 89, 56};
        int k=3;
        printMax(arr,k);
    }
}
