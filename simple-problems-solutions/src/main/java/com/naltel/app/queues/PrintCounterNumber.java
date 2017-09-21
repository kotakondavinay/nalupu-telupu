package com.naltel.app.queues;

import java.util.LinkedList;
import java.util.Queue;

public class PrintCounterNumber {

    public static void printNumbers(Queue<Integer> q1, Queue<Integer> q2, int n) {
        if(n == 0) return;

        int oldValue = q1.peek();
        int counter = 0;
        while (!q1.isEmpty()) {
            int currentValue = q1.poll();
            if(oldValue == currentValue) {
                counter++;
            } else {
                q2.add(counter);
                q2.add(oldValue);
                System.out.print(counter);
                System.out.print(oldValue);
                counter = 1;
                oldValue = currentValue;
            }
        }
        q2.add(counter);
        q2.add(oldValue);
        System.out.print(counter);
        System.out.print(oldValue);
        System.out.println();
        printNumbers(q2, q1, n-1);

    }

    public static void printNumbers2(Queue<Integer> q1, Queue<Integer> q2, int n) {
        Queue<Integer> t1;
        Queue<Integer> t2;
        for (int i = 0; i < n ; i++) {

            if(q1.isEmpty()) {
                t1 = q1;
                t2 = q2;
            } else {
                t1 = q2;
                t2 = q1;
            }

                int oldValue = q1.peek();
            int counter = 0;
            while (!t1.isEmpty()) {
                int currentValue = t1.poll();
                if(oldValue == currentValue) {
                    counter++;
                } else {
                    t2.add(counter);
                    t2.add(oldValue);
                    System.out.print(counter);
                    System.out.print(oldValue);
                    counter = 1;
                    oldValue = currentValue;
                }
            }
            t2.add(counter);
            t2.add(oldValue);
            System.out.print(counter);
            System.out.print(oldValue);
            System.out.println();
        }

    }

    public static void main(String[] args) {
        Queue<Integer> q1 = new LinkedList<Integer>();
        Queue<Integer> q2 = new LinkedList<Integer>();
        q1.add(1);
        System.out.println(1);
        printNumbers(q1, q2, 9);
        System.out.println();
        Queue<Integer> q3 = new LinkedList<Integer>();
        Queue<Integer> q4 = new LinkedList<Integer>();
        q3.add(1);
        System.out.println(1);
        printNumbers(q3, q4, 9);


    }
}
