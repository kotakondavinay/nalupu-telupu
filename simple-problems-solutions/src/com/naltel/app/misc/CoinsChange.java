/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naltel.app.misc;

import java.util.*;

/**
 *
 * @author vinaykk
 */
public class CoinsChange {
    
    public static void getCoinsChange(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] coins = new int[m];
        for(int i =0; i < m; i++) {
            coins[i] = in.nextInt();
        }
        //Collections.sort(coins);
        long value = getCoinsChangeHelper(coins, n);
        System.out.println(value);   
    }
    
    public static long getCoinsChangeHelper(int[] coins, int n) {
        if(n < 0) return 0;
        if(n == 0) return 1;
        //int returnVal = 0;
        
        long[] coinsNumber = new long[n+1];
        for(int i = 0; i <= n ; i++) coinsNumber[i] = 0;
        coinsNumber[0] = 1;
        for(int i = 0 ; i < coins.length; i++) {
            for(int j = coins[i]; j <= n ; j++) {
                coinsNumber[j] += coinsNumber[j-coins[i]];
            }
            //returnVal += getCoinsChangeHelper(coins, n-coins[i]);
        }
        return coinsNumber[n];
    }
    
}
