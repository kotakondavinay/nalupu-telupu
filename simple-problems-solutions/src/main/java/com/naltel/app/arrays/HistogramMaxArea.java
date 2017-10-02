package com.naltel.app.arrays;

import java.util.Stack;

public class HistogramMaxArea {
    public static int maxAreaHistogram(int[] array) {

        Stack<Integer> stack = new Stack<Integer>();
        int i=0, maxArea = 0;
        while(i < array.length) {
            if(stack.isEmpty() || array[stack.peek()] <= array[i]) {
                stack.push(i++);
            } else {
                int temp = stack.pop();
                int tempArea;
                if(stack.isEmpty()) {
                    tempArea = i * array[temp];
                } else {
                    tempArea = (i - stack.peek() - 1 )* array[temp];
                }
                if(maxArea < tempArea) {
                    maxArea = tempArea;
                }
            }
        }

        while(!stack.isEmpty()) {
            int temp = stack.pop();
            int tempArea;
            if(stack.isEmpty()) {
                tempArea = i * array[temp];
            } else {
                tempArea = (i - stack.peek() - 1 )* array[temp];
            }
            if(maxArea < tempArea) {
                maxArea = tempArea;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int array[] = { 3, 2, 4, 5, 4, 5, 1, 6 };
        int maxArea = maxAreaHistogram(array);
        System.out.println("MaxHistogram Area is " + maxArea);
    }
}
