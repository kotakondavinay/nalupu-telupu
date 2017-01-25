/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naltel.app.misc;

/**
 *
 * @author vinaykk
 */
public class LongestSubSequenceDistanceOne {
 
    public static int getLongestSubSequenceDistance(int[] array, int m) {
        int[] dp= new int[m];
        for(int i=0; i<m;i++) {
            dp[i] = 1;
        }
        for(int i = 1; i < m; i++) {
            for(int j = 0; j < i; j++) {
                if(array[i] == array[j] + 1 || array[i] == array[j]-1) {
                    dp[i] = (dp[i] > (dp[j] +1) ? dp[i] : (dp[j]+1));
                }
            }
        }
        int result = 1;
        for(int i = 0; i < m; i++){
            if(result < dp[i]) result = dp[i];
        }
        return result;
    }
}
