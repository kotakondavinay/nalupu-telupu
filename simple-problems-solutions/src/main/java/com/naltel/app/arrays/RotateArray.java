package com.naltel.app.arrays;

public class RotateArray {
    public static void rotateleft(int[] a, int k) {
        k = k% a.length;
        int count = 0;
        for(int i = 0; count < a.length; i++) {
            int current = i;
            int temp = a[current];
            int next = (current + k) % a.length;
            int prev = current;
            while(next != i) {
                a[prev] = a[next];
                prev = next;
                next = (next + k) % a.length;
                count += 1;
            }
            a[prev] = temp;
            printarr(a);
        }
    }

    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }

    public static void printarr(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8};
        printarr(arr);
        System.out.println();
        //rotateleft(arr, 3);
        rotate(arr, 3);
        printarr(arr);
        System.out.println();
    }
}
