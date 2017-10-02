package com.naltel.app.arrays;

import java.util.HashMap;
import java.util.Map;

public class SumSubarrays {

    public static void sumSubarray(int[] a, int sum) {

        Map<Integer, Integer> sumMap = new HashMap<Integer, Integer>();
        int currentSum = 0;
        for(int i = 0; i < a.length; i++) {
            currentSum += a[i];
            if(currentSum == sum) {
                System.out.println("Sum found at 0 and "+i);
                break;
            }
            if(sumMap.containsKey(currentSum - sum)) {
                System.out.println("Sum found at"+ (sumMap.get(currentSum - sum)+1) + " and "+i);
                break;
            }
            sumMap.putIfAbsent(currentSum - sum, i);
        }
        System.out.println("No Subarray found with the given sum");
    }
    public static void main(String[] args) {
        int arr[] = {3, 9, 4, 6, -1, 2, -7};
        sumSubarray(arr, 7);

    }
}
