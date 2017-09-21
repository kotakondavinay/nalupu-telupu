package com.naltel.app.arrays;

public class ArrayRearrangePosNeg {

    public static void rearrange(int arr[]) {
        int first = 0, temp, second = 0;
        for(second = 0; second < arr.length; second++) {
            if(arr[second] < 0) {
                temp = arr[second];
                arr[second] = arr[first];
                arr[first] = temp;
                first++;
            }
        }
        second = first;
        first = 0;
        while(first < second && second < arr.length && arr[first] < 0) {
            temp = arr[second];
            arr[second] = arr[first];
            arr[first] = temp;
            first +=2;
            second++;
        }
    }
    public static void print(int arr[]) {
        for(int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    public static void main(String[] args) {
        int arr[] = {-1, -2, -3,-4, -5, -6, 7, 8, 9, 10, 11, 12};
        print(arr);
        rearrange(arr);
        print(arr);
    }
}
